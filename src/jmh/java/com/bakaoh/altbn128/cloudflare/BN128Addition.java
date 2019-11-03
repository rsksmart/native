package com.bakaoh.altbn128.cloudflare;

import com.bakaoh.altbn128.PrecompiledContract;

import static com.bakaoh.altbn128.current.ByteUtil.*;

/**
 * Created by bakaking on 29/10/2019.
 */
public class BN128Addition extends PrecompiledContract {

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
