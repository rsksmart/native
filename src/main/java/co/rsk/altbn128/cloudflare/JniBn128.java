package co.rsk.altbn128.cloudflare;

public class JniBn128 {

    private static final Throwable loadError; // null, if library has been loaded successfully, otherwise - holds error details

    public native int add(byte[] input, int len, byte[] output);

    public native int mul(byte[] input, int len, byte[] output);

    public native int pairing(byte[] input, int len, byte[] output);

    static {
        Throwable error = null;
        try {
            NativeLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
            error = e;
        }
        loadError = error;
    }

    public static Throwable getLoadError() {
        return loadError;
    }
}
