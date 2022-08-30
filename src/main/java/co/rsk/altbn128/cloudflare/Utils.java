package co.rsk.altbn128.cloudflare;

public class Utils {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String OS_ARCH = System.getProperty("os.arch").toLowerCase();

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isLinuxAmd64() {
        return (OS.indexOf("nux") >= 0 && OS_ARCH.indexOf("amd64") >= 0);
    }

    public static boolean isLinuxArm64() {
        return (OS.indexOf("nux") >= 0) && OS_ARCH.indexOf("amd64") >= 0;
    }
}