package co.rsk.bls12_381;

import co.rsk.NativeLoader;
import com.sun.jna.Library;
import com.sun.jna.ptr.IntByReference;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BLS12_381 implements Library {
    public static final boolean ENABLED;

    static {
        boolean enabled;
        try {
            NativeLoader.registerJNA(BLS12_381.class, "eth_pairings");
            enabled = true;
        } catch (final Throwable t) {
            enabled = false;
        }
        ENABLED = enabled;
    }

    public static final int EIP2537_PREALLOCATE_FOR_ERROR_BYTES = 256;
    public static final int EIP2537_PREALLOCATE_FOR_RESULT_BYTES = 256;

    public static final byte BLS12_G1ADD_OPERATION_RAW_VALUE = 1;
    public static final byte BLS12_G1MUL_OPERATION_RAW_VALUE = 2;
    public static final byte BLS12_G1MULTIEXP_OPERATION_RAW_VALUE = 3;
    public static final byte BLS12_G2ADD_OPERATION_RAW_VALUE = 4;
    public static final byte BLS12_G2MUL_OPERATION_RAW_VALUE = 5;
    public static final byte BLS12_G2MULTIEXP_OPERATION_RAW_VALUE = 6;
    public static final byte BLS12_PAIR_OPERATION_RAW_VALUE = 7;
    public static final byte BLS12_MAP_FP_TO_G1_OPERATION_RAW_VALUE = 8;
    public static final byte BLS12_MAP_FP2_TO_G2_OPERATION_RAW_VALUE = 9;

    public static byte[] g1Add(byte[] data) throws BLS12_381Exception {
     return performOperation(BLS12_G1ADD_OPERATION_RAW_VALUE, data);
    }

    public static byte[] g1Mul(byte[] data) throws BLS12_381Exception {
        return performOperation(BLS12_G1MUL_OPERATION_RAW_VALUE, data);
    }

    public static byte[] g1MultiExp(byte[] data) throws BLS12_381Exception {
        return performOperation(BLS12_G1MULTIEXP_OPERATION_RAW_VALUE, data);
    }

    public static byte[] g2Add(byte[] data) throws BLS12_381Exception {
        return performOperation(BLS12_G2ADD_OPERATION_RAW_VALUE, data);
    }

    public static byte[] g2Mul(byte[] data) throws BLS12_381Exception {
        return performOperation(BLS12_G2MUL_OPERATION_RAW_VALUE, data);
    }

    public static byte[] g2MultiExp(byte[] data) throws BLS12_381Exception {
        return performOperation(BLS12_G2MULTIEXP_OPERATION_RAW_VALUE, data);
    }

    public static byte[] mapFpToG1(byte[] data) throws BLS12_381Exception {
        return performOperation(BLS12_MAP_FP_TO_G1_OPERATION_RAW_VALUE, data);
    }

    public static byte[] mapFp2ToG2(byte[] data) throws BLS12_381Exception {
        return performOperation(BLS12_MAP_FP2_TO_G2_OPERATION_RAW_VALUE, data);
    }

    public static byte[] pair(byte[] data) throws BLS12_381Exception {
        return performOperation(BLS12_PAIR_OPERATION_RAW_VALUE, data);
    }

    private static byte[] performOperation(byte operationId, byte[] data) throws BLS12_381Exception {
        final byte[] result = new byte[BLS12_381.EIP2537_PREALLOCATE_FOR_RESULT_BYTES];
        final byte[] error = new byte[BLS12_381.EIP2537_PREALLOCATE_FOR_ERROR_BYTES];

        final IntByReference o_len = new IntByReference(BLS12_381.EIP2537_PREALLOCATE_FOR_RESULT_BYTES);
        final IntByReference err_len = new IntByReference(BLS12_381.EIP2537_PREALLOCATE_FOR_ERROR_BYTES);

        final int errorNo = BLS12_381.eip2537_perform_operation(operationId, data, data.length, result, o_len, error, err_len);

        if (errorNo == 0) {
            //we need to truncate the result array to the actual length
            return Arrays.copyOfRange(result, 0, o_len.getValue());
        }

        String reason = new String(Arrays.copyOfRange(error, 0, err_len.getValue()), StandardCharsets.UTF_8);

        throw new BLS12_381Exception(reason);
    }

    private static native int eip2537_perform_operation(
            byte op,
            byte[] i,
            int i_len,
            byte[] o,
            IntByReference o_len,
            byte[] err,
            IntByReference err_len
    );
}