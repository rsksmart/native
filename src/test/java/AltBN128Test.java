import co.rsk.altbn128.JniBn128;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AltBN128Test {
    private JniBn128 jniBn128;

    @Before
    public void setup() {
        jniBn128 = new JniBn128();
    }

    @Test
    public void add() {
        byte[] data = TestUtils.toByteArray("0000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000002");
        byte[] expectedOutput = TestUtils.toByteArray("030644e72e131a029b85045b68181585d97816a916871ca8d3c208c16d87cfd315ed738c0e0a7c92e7845f96b2ae9c0a68a6a449e3538fc7ff3ebf7a5a18a2c4");
        byte[] jniOutput = new byte[64];

        int res = jniBn128.add(data,data.length, jniOutput);

        Assert.assertEquals(1, res);
        Assert.assertArrayEquals(expectedOutput, jniOutput);
    }

    @Test
    public void mul() {
        byte[] data = TestUtils.toByteArray("00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000002ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        byte[] expectedOutput = TestUtils.toByteArray("2f588cffe99db877a4434b598ab28f81e0522910ea52b45f0adaa772b2d5d35212f42fa8fd34fb1b33d8c6a718b6590198389b26fc9d8808d971f8b009777a97");
        byte[] jniOutput = new byte[64];

        int res = jniBn128.mul(data,data.length, jniOutput);

        Assert.assertEquals(1, res);
        Assert.assertArrayEquals(expectedOutput, jniOutput);
    }

    @Test
    public void pairing() {
        byte[] data = TestUtils.toByteArray("00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000002198e9393920d483a7260bfb731fb5d25f1aa493335a9e71297e485b7aef312c21800deef121f1e76426a00665e5c4479674322d4f75edadd46debd5cd992f6ed090689d0585ff075ec9e99ad690c3395bc4b313370b38ef355acdadcd122975b12c85ea5db8c6deb4aab71808dcb408fe3d1e7690c43d37b4ce6cc0166fa7daa000000000000000000000000000000000000000000000000000000000000000130644e72e131a029b85045b68181585d97816a916871ca8d3c208c16d87cfd45198e9393920d483a7260bfb731fb5d25f1aa493335a9e71297e485b7aef312c21800deef121f1e76426a00665e5c4479674322d4f75edadd46debd5cd992f6ed090689d0585ff075ec9e99ad690c3395bc4b313370b38ef355acdadcd122975b12c85ea5db8c6deb4aab71808dcb408fe3d1e7690c43d37b4ce6cc0166fa7daa");
        byte[] expectedOutput = TestUtils.toByteArray("0000000000000000000000000000000000000000000000000000000000000001");
        byte[] jniOutput = new byte[32];

        int res = jniBn128.pairing(data,data.length, jniOutput);

        Assert.assertEquals(1, res);
        Assert.assertArrayEquals(expectedOutput, jniOutput);
    }
}
