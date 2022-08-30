package co.rsk.altbn128.cloudflare;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NativeLoader {

    public static boolean load() throws Exception {

        // Load the os-dependent library from the jar file
        String nativeLibraryName = System.mapLibraryName("bn128");
        String nativeLibraryPath = "/co/rsk/altbn128/cloudflare/native";
        if (Utils.isWindows()) {
            nativeLibraryPath = nativeLibraryPath + "/win";
        } else if (Utils.isLinuxAmd64()) {
            nativeLibraryPath = nativeLibraryPath + "/linux";
        } else if (Utils.isLinuxArm64()) {
            nativeLibraryPath = nativeLibraryPath + "/linux/arm64";
        } else if (Utils.isMac()) {
            nativeLibraryPath = nativeLibraryPath + "/macos";
        }

        if (JniBn128.class.getResource(nativeLibraryPath + "/" + nativeLibraryName) == null) {
            throw new Exception("Error loading native library: " + nativeLibraryPath + "/" + nativeLibraryName);
        }

        // Temporary library folder
        String tempFolder = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();

        // Extract resource files
        return extractAndLoadLibraryFile(nativeLibraryPath, nativeLibraryName, tempFolder);
    }


    private static boolean extractAndLoadLibraryFile(String libFolderForCurrentOS, String libraryFileName, String targetFolder) {
        String nativeLibraryFilePath = libFolderForCurrentOS + "/" + libraryFileName;
        String extractedLibFileName = libraryFileName;
        File extractedLibFile = new File(targetFolder, extractedLibFileName);

        try {
            if (extractedLibFile.exists()) {
                // test md5sum value
                String md5sum1 = md5sum(JniBn128.class.getResourceAsStream(nativeLibraryFilePath));
                String md5sum2 = md5sum(new FileInputStream(extractedLibFile));

                if (md5sum1.equals(md5sum2)) {
                    return loadNativeLibrary(targetFolder, extractedLibFileName);
                } else {
                    // remove old native library file
                    boolean deletionSucceeded = extractedLibFile.delete();
                    if (!deletionSucceeded) {
                        throw new IOException("failed to remove existing native library file: " + extractedLibFile.getAbsolutePath());
                    }
                }
            }

            // Extract file into the current directory
            InputStream reader = JniBn128.class.getResourceAsStream(nativeLibraryFilePath);
            FileOutputStream writer = new FileOutputStream(extractedLibFile);
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, bytesRead);
            }

            writer.close();
            reader.close();

            if (!System.getProperty("os.name").contains("Windows")) {
                try {
                    Runtime.getRuntime().exec(new String[]{"chmod", "755", extractedLibFile.getAbsolutePath()}).waitFor();
                } catch (Throwable e) {
                }
            }

            return loadNativeLibrary(targetFolder, extractedLibFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    private static synchronized boolean loadNativeLibrary(String path, String name) {
        File libPath = new File(path, name);
        if (libPath.exists()) {
            try {
                System.load(libPath.getAbsolutePath());
                return true;
            } catch (UnsatisfiedLinkError e) {
                System.err.println(e);
                return false;
            }
        }
        return false;
    }

    private static String md5sum(InputStream input) throws IOException {
        BufferedInputStream in = new BufferedInputStream(input);

        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            DigestInputStream digestInputStream = new DigestInputStream(in, digest);
            for (; digestInputStream.read() >= 0; ) {
            }
            ByteArrayOutputStream md5out = new ByteArrayOutputStream();
            md5out.write(digest.digest());
            return md5out.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm is not available: " + e);
        } finally {
            in.close();
        }
    }

}