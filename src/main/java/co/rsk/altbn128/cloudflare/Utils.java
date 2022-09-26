package co.rsk.altbn128.cloudflare;

public class Utils {
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();
    private static final String OS_ARCH = System.getProperty("os.arch").toLowerCase();

    public static boolean isWindows() {
        return OS_NAME.contains("win");
    }

    public static boolean isMac() {
        return OS_NAME.contains("mac");
    }

    public static boolean isLinux() {
        return OS_NAME.contains("nux");
    }

    public static boolean isArm() {
        return OS_ARCH.contains("arm") || OS_ARCH.contains("aarch");
    }
}
