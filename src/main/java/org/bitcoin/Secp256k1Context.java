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

/**
 * This class holds the context reference used in native methods
 * to handle ECDSA operations.
 */
public class Secp256k1Context {
    private static final boolean enabled; // true if the library is loaded
    private static final long context; // ref to pointer to context obj
    private static final Throwable loadError; // null, if library has been loaded successfully, otherwise - holds error details

    static { //static initializer
        boolean isEnabled = true;
        long contextRef = -1;
        Throwable error = null;
        try {
            if ("The Android Project".equals(System.getProperty("java.vm.vendor"))) {
                System.loadLibrary("co/rsk/secp256k1");
            } else {
                Secp256k1Loader.initialize();
            }
            contextRef = secp256k1_init_context();
        } catch (UnsatisfiedLinkError | Exception e) {
            isEnabled = false;
            error = e;
        }
        enabled = isEnabled;
        context = contextRef;
        loadError = error;
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static long getContext() {
        if (!enabled) return -1; //sanity check
        return context;
    }

    public static Throwable getLoadError() {
        return loadError;
    }

    private static native long secp256k1_init_context();
}
