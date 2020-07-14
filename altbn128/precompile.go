package main

// #include <jni.h>
// #include <stdlib.h>
// static jbyte* getCByteArray(JNIEnv *env, jbyteArray input) {
//     return (*env)->GetByteArrayElements(env, input, NULL);
// }
// static void releaseCByteArray(JNIEnv *env, jbyteArray input, jbyte* cData) {
//     (*env)->ReleaseByteArrayElements(env, input, cData, 0);
// }
// static void setCByteArray(JNIEnv *env, char *input, jint len, jbyteArray output) {
//	   (*env)->SetByteArrayRegion(env, output, 0, len, input);
// }
import "C"
import (
	"errors"
	"math/big"
	"unsafe"

	bn256 "./bn256"
)

//export Java_co_rsk_altbn128_cloudflare_JniBn128_add
func Java_co_rsk_altbn128_cloudflare_JniBn128_add(env *C.JNIEnv, clazz C.jclass, x C.jbyteArray, len C.jint, y C.jbyteArray) C.jint {
	cData := C.getCByteArray(env, x)
	slice := C.GoBytes(unsafe.Pointer(cData), len)
	defer C.releaseCByteArray(env, x, cData)
	output, err := runBn256Add(slice)
	if err != nil {
		return -1
	}

	cOutput := C.CString(string(output))
	defer C.free(unsafe.Pointer(cOutput))
	C.setCByteArray(env, cOutput, 64, y)
	return 1
}

//export Java_co_rsk_altbn128_cloudflare_JniBn128_mul
func Java_co_rsk_altbn128_cloudflare_JniBn128_mul(env *C.JNIEnv, clazz C.jclass, x C.jbyteArray, len C.jint, y C.jbyteArray) C.jint {
	cData := C.getCByteArray(env, x)
	slice := C.GoBytes(unsafe.Pointer(cData), len)
	defer C.releaseCByteArray(env, x, cData)
	output, err := runBn256ScalarMul(slice)
	if err != nil {
		return -1
	}

	cOutput := C.CString(string(output))
	defer C.free(unsafe.Pointer(cOutput))
	C.setCByteArray(env, cOutput, 64, y)
	return 1
}

//export Java_co_rsk_altbn128_cloudflare_JniBn128_pairing
func Java_co_rsk_altbn128_cloudflare_JniBn128_pairing(env *C.JNIEnv, clazz C.jclass, x C.jbyteArray, len C.jint, y C.jbyteArray) C.jint {
	cData := C.getCByteArray(env, x)
	slice := C.GoBytes(unsafe.Pointer(cData), len)
	defer C.releaseCByteArray(env, x, cData)
	output, err := runBn256Pairing(slice)
	if err != nil {
		return -1
	}

	cOutput := C.CString(string(output))
	defer C.free(unsafe.Pointer(cOutput))
	C.setCByteArray(env, cOutput, 32, y)
	return 1
}

func main() {} // a dummy function

var (
	// true32Byte is returned if the bn256 pairing check succeeds.
	true32Byte = []byte{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}

	// false32Byte is returned if the bn256 pairing check fails.
	false32Byte = make([]byte, 32)

	// errBadPairingInput is returned if the bn256 pairing input is invalid.
	errBadPairingInput = errors.New("bad elliptic curve pairing size")
)

// runBn256Add implements the Bn256Add precompile, referenced by both
// Byzantium and Istanbul operations.
func runBn256Add(input []byte) ([]byte, error) {
	x, err := newCurvePoint(getData(input, 0, 64))
	if err != nil {
		return nil, err
	}
	y, err := newCurvePoint(getData(input, 64, 64))
	if err != nil {
		return nil, err
	}
	res := new(bn256.G1)
	res.Add(x, y)
	return res.Marshal(), nil
}

// runBn256ScalarMul implements the Bn256ScalarMul precompile, referenced by
// both Byzantium and Istanbul operations.
func runBn256ScalarMul(input []byte) ([]byte, error) {
	p, err := newCurvePoint(getData(input, 0, 64))
	if err != nil {
		return nil, err
	}
	res := new(bn256.G1)
	res.ScalarMult(p, new(big.Int).SetBytes(getData(input, 64, 32)))
	return res.Marshal(), nil
}

// runBn256Pairing implements the Bn256Pairing precompile, referenced by both
// Byzantium and Istanbul operations.
func runBn256Pairing(input []byte) ([]byte, error) {
	// Handle some corner cases cheaply
	if len(input)%192 > 0 {
		return nil, errBadPairingInput
	}
	// Convert the input into a set of coordinates
	var (
		cs []*bn256.G1
		ts []*bn256.G2
	)
	for i := 0; i < len(input); i += 192 {
		c, err := newCurvePoint(input[i : i+64])
		if err != nil {
			return nil, err
		}
		t, err := newTwistPoint(input[i+64 : i+192])
		if err != nil {
			return nil, err
		}
		cs = append(cs, c)
		ts = append(ts, t)
	}
	// Execute the pairing checks and return the results
	if bn256.PairingCheck(cs, ts) {
		return true32Byte, nil
	}
	return false32Byte, nil
}

// newCurvePoint unmarshals a binary blob into a bn256 elliptic curve point,
// returning it, or an error if the point is invalid.
func newCurvePoint(blob []byte) (*bn256.G1, error) {
	p := new(bn256.G1)
	if _, err := p.Unmarshal(blob); err != nil {
		return nil, err
	}
	return p, nil
}

// newTwistPoint unmarshals a binary blob into a bn256 elliptic curve point,
// returning it, or an error if the point is invalid.
func newTwistPoint(blob []byte) (*bn256.G2, error) {
	p := new(bn256.G2)
	if _, err := p.Unmarshal(blob); err != nil {
		return nil, err
	}
	return p, nil
}

func getData(data []byte, start uint64, size uint64) []byte {
	length := uint64(len(data))
	if start > length {
		start = length
	}
	end := start + size
	if end > length {
		end = length
	}
	return rightPadBytes(data[start:end], int(size))
}

func rightPadBytes(slice []byte, l int) []byte {
	if l <= len(slice) {
		return slice
	}

	padded := make([]byte, l)
	copy(padded, slice)

	return padded
}
