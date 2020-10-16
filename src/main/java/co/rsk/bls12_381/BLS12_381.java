package co.rsk.bls12_381;

import co.rsk.NativeLoader;
import com.sun.jna.Library;
import com.sun.jna.ptr.IntByReference;

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

    public static int g1Add(byte[] data) {
     return performOperation(BLS12_G1ADD_OPERATION_RAW_VALUE, data);
    }

    public static int g1Mul(byte[] data) {
        return performOperation(BLS12_G1MUL_OPERATION_RAW_VALUE, data);
    }

    public static int g1MultiExp(byte[] data){
        return performOperation(BLS12_G1MULTIEXP_OPERATION_RAW_VALUE, data);
    }

    public static int g2Add(byte[] data) {
        return performOperation(BLS12_G2ADD_OPERATION_RAW_VALUE, data);
    }

    public static int g2Mul(byte[] data) {
        return performOperation(BLS12_G2MUL_OPERATION_RAW_VALUE, data);
    }

    public static int g2MultiExp(byte[] data) {
        return performOperation(BLS12_G2MULTIEXP_OPERATION_RAW_VALUE, data);
    }

    public static int mapFpToG1(byte[] data) {
        return performOperation(BLS12_MAP_FP_TO_G1_OPERATION_RAW_VALUE, data);
    }

    public static int mapFp2ToG2(byte[] data) {
        return performOperation(BLS12_MAP_FP2_TO_G2_OPERATION_RAW_VALUE, data);
    }

    public static int pair(byte[] data) {
        return performOperation(BLS12_PAIR_OPERATION_RAW_VALUE, data);
    }

    private static int performOperation(byte operationId, byte[] data) {
        final byte[] result = new byte[BLS12_381.EIP2537_PREALLOCATE_FOR_RESULT_BYTES];
        final byte[] error = new byte[BLS12_381.EIP2537_PREALLOCATE_FOR_ERROR_BYTES];

        final IntByReference o_len = new IntByReference(BLS12_381.EIP2537_PREALLOCATE_FOR_RESULT_BYTES);
        final IntByReference err_len = new IntByReference(BLS12_381.EIP2537_PREALLOCATE_FOR_ERROR_BYTES);

        return BLS12_381.eip2537_perform_operation(operationId, data, data.length, result, o_len, error, err_len);
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