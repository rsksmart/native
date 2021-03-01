package co.rsk.altbn128;

import co.rsk.NativeLoader;

public class JniBn128 {
    public native int add(byte[] input, int len, byte[] output);

    public native int mul(byte[] input, int len, byte[] output);

    public native int pairing(byte[] input, int len, byte[] output);

    static {
        try {
            if (NativeLoader.registerJNI(JniBn128.class, "bn128")) {
                System.out.println("Successfully loaded.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}