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
import java.util.Collections;

public class NativeSecp256k1Util{
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * returns a (r.length + s.length) bytes array long
     *
     * @param sig {r,s}
     * @param fixed 64 bytes array (32 from r, 32 from s)
     * @return r + s (bytes array)
     */
    /**
     *
     * @param r
     * @param s
     * @return
     */
    public static byte[] concatenate(byte[] r, byte[] s) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[r.length + s.length]);
        buffer.put(Arrays.copyOfRange(r, 0, r.length));
        buffer.put(Arrays.copyOfRange(s, 0, s.length));
        return buffer.array();
    }

    public static byte[] concatenate(BigInteger r, BigInteger s) {
        return concatenate(r.toByteArray(), s.toByteArray());
    }

    // Testing
    public static String zero32String() {
        return String.join("", Collections.nCopies(64, "0")).toLowerCase();
    }
}
