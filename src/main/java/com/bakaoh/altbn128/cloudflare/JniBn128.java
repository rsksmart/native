package com.bakaoh.altbn128.cloudflare;

/**
 * Created by bakaking on 28/10/2019.
 */
public class JniBn128 {
    public native int add(byte[] input, int len, byte[] output);

    public native int mul(byte[] input, int len, byte[] output);

    public native int pairing(byte[] input, int len, byte[] output);

    static {
        try {
            if (NativeLoader.load()) {
                System.out.println("Successfully loaded.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}