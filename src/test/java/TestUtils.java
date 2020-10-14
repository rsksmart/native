import javax.xml.bind.DatatypeConverter;

public class TestUtils {
    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }
}
