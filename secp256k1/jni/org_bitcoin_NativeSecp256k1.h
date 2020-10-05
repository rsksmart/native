/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_bitcoin_NativeSecp256k1 */

#ifndef _Included_org_bitcoin_NativeSecp256k1
#define _Included_org_bitcoin_NativeSecp256k1
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ctx_clone
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ctx_1clone
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_context_randomize
 * Signature: (Ljava/nio/ByteBuffer;J)I
 */
JNIEXPORT jint JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1context_1randomize
  (JNIEnv *, jclass, jobject, jlong);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_privkey_negate
 * Signature: (Ljava/nio/ByteBuffer;J)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1privkey_1negate
  (JNIEnv *, jclass, jobject, jlong);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_privkey_tweak_add
 * Signature: (Ljava/nio/ByteBuffer;J)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1privkey_1tweak_1add
  (JNIEnv *, jclass, jobject, jlong);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_privkey_tweak_mul
 * Signature: (Ljava/nio/ByteBuffer;J)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1privkey_1tweak_1mul
  (JNIEnv *, jclass, jobject, jlong);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_pubkey_negate
 * Signature: (Ljava/nio/ByteBuffer;JI)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1pubkey_1negate
  (JNIEnv *, jclass, jobject, jlong, jint);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_pubkey_tweak_add
 * Signature: (Ljava/nio/ByteBuffer;JI)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1pubkey_1tweak_1add
  (JNIEnv *, jclass, jobject, jlong, jint);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_pubkey_tweak_mul
 * Signature: (Ljava/nio/ByteBuffer;JI)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1pubkey_1tweak_1mul
  (JNIEnv *, jclass, jobject, jlong, jint);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_destroy_context
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1destroy_1context
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ecdsa_verify
 * Signature: (Ljava/nio/ByteBuffer;JII)I
 */
JNIEXPORT jint JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ecdsa_1verify
  (JNIEnv *, jclass, jobject, jlong, jint, jint);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ecdsa_sign
 * Signature: (Ljava/nio/ByteBuffer;J)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ecdsa_1sign
  (JNIEnv *, jclass, jobject, jlong);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ecdsa_sign_compact
 * Signature: (Ljava/nio/ByteBuffer;J)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ecdsa_1sign_1compact
  (JNIEnv *, jclass, jobject, jlong);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ec_seckey_verify
 * Signature: (Ljava/nio/ByteBuffer;J)I
 */
JNIEXPORT jint JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ec_1seckey_1verify
  (JNIEnv *, jclass, jobject, jlong);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ec_pubkey_create
 * Signature: (Ljava/nio/ByteBuffer;JI)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ec_1pubkey_1create
  (JNIEnv *, jclass, jobject, jlong, jint);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ec_pubkey_parse
 * Signature: (Ljava/nio/ByteBuffer;JI)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ec_1pubkey_1parse
  (JNIEnv *, jclass, jobject, jlong, jint);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ec_pubkey_add
 * Signature: (Ljava/nio/ByteBuffer;JII)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ec_1pubkey_1add
  (JNIEnv *, jclass, jobject, jlong, jint, jint);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ecdh
 * Signature: (Ljava/nio/ByteBuffer;JI)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ecdh
  (JNIEnv *, jclass, jobject, jlong, jint);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_ecdsa_recover
 * Signature: (Ljava/nio/ByteBuffer;JII)[[B
 */
JNIEXPORT jobjectArray JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1ecdsa_1recover
  (JNIEnv *, jclass, jobject, jlong, jint, jint);

/*
 * Class:     org_bitcoin_NativeSecp256k1
 * Method:    secp256k1_is_infinity
 * Signature: (Ljava/nio/ByteBuffer;JI)I
 */
JNIEXPORT jint JNICALL Java_org_bitcoin_NativeSecp256k1_secp256k1_1is_1infinity
  (JNIEnv *, jclass, jobject, jlong, jint);

#ifdef __cplusplus
}
#endif
#endif
