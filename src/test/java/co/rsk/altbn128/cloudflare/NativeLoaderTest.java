package co.rsk.altbn128.cloudflare;

import org.junit.Assert;
import org.junit.Test;

public class NativeLoaderTest {
    @Test
    public void testNativeLoaderForCurrentOS()  throws Exception {
        Assert.assertTrue(NativeLoader.load());
    }
}
