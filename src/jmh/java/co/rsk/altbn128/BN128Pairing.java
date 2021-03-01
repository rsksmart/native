package co.rsk.altbn128;


public class BN128Pairing extends PrecompiledContract {

    private static final int PAIR_SIZE = 192;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    @Override
    public long getGasForData(byte[] data) {

        if (data == null) {
            return 100000;
        }

        return 80000L * (data.length / PAIR_SIZE) + 100000L;
    }

    @Override
    public byte[] execute(byte[] data) {
        if (data == null) {
            data = EMPTY_BYTE_ARRAY;
        }
        byte[] output = new byte[32];
        int rs = new JniBn128().pairing(data, data.length, output);
        if (rs < 0) {
            return EMPTY_BYTE_ARRAY;
        }
        return output;
    }
}
