/*
 * Copyright 2014-2016 the libsecp256k1 contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoin;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class NativeSecp256k1Util{
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * If "fixed" returns a 64 byte array long
     * if not "fixed" returns a (r.length + s.length) bytes array long
     *
     * Note: When fixed, we take 32 bytes from "r" and 32 bytes from "s".
     *
     * @param sig {r,s}
     * @param fixed 64 bytes array (32 from r, 32 from s)
     * @return r + s (bytes array)
     */
    /**
     *
     * @param r
     * @param s
     * @param fixed
     * @return
     */
    public static byte[] concatenate(BigInteger r, BigInteger s, boolean fixed) {
        byte[] rBytes = r.toByteArray();
        byte[] sBytes = s.toByteArray();
        byte[] allByteArray = new byte[fixed ? 64 : rBytes.length + sBytes.length];
        ByteBuffer buff = ByteBuffer.wrap(allByteArray);
        if(fixed) {
            for (int i = rBytes.length; i < 32; i++) {
                buff.put((byte) 0);
            }
        }
        buff.put(Arrays.copyOfRange(rBytes, getStartIndex(rBytes, fixed), rBytes.length));
        if(fixed) {
            for (int i = sBytes.length; i < 32; i++) {
                buff.put((byte) 0);
            }
        }
        buff.put(Arrays.copyOfRange(sBytes, getStartIndex(sBytes, fixed), sBytes.length));
        return buff.array();
    }

    /**
     *  If bytes length  is greater than 32, we keep the last 32 bytes at the right.
     *          - So starting byte index will be = length - 32.
     *  If not
     *          -  Starting byte index = 0.
     * @param sBytes
     * @return
     */
    private static int getStartIndex(byte[] sBytes, boolean fixed) {
        return sBytes.length > 32 && fixed ? sBytes.length - 32 : 0;
    }
}
