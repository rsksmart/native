package co.rsk.altbn128;


public class BN128Addition extends PrecompiledContract {

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    @Override
    public long getGasForData(byte[] data) {
        return 500;
    }

    @Override
    public byte[] execute(byte[] data) {
        if (data == null) {
            data = EMPTY_BYTE_ARRAY;
        }
        byte[] output = new byte[64];
        int rs = new JniBn128().add(data, data.length, output);
        if (rs < 0) {
            return EMPTY_BYTE_ARRAY;
        }
        return output;
    }
}
