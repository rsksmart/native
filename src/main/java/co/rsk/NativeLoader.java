package co.rsk;

import com.sun.jna.Native;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class NativeLoader {

    /**
     * Registers a native library using classic JNI process
     * @param jniClass
     * @param libName
     * @return
     * @throws Exception
     */
    public static boolean registerJNI(Class jniClass, String libName) throws Exception {
        // Load the os-dependent library from the jar file
        String nativeLibraryName = System.mapLibraryName(libName);
        String nativeLibraryPath = libFolderForCurrentOs(packageName(jniClass));

        if (jniClass.getResource(nativeLibraryPath + "/" + nativeLibraryName) == null) {
            throw new Exception("Error loading native library: " + nativeLibraryPath + "/" + nativeLibraryName);
        }

        // Temporary library folder
        String tempFolder = tempFolderPath();

        // Extract resource files
        return extractAndLoadLibraryFile(nativeLibraryPath, nativeLibraryName, tempFolder, jniClass);
    }

    private static String libFolderForCurrentOs(String packageName) {
        String nativeLibraryPath = "/co/rsk/" + packageName;
        if (Utils.isWindows()) {
            nativeLibraryPath = nativeLibraryPath + "/win";
        } else if (Utils.isLinux()) {
            nativeLibraryPath = nativeLibraryPath + "/linux";
        } else if (Utils.isMac()) {
            nativeLibraryPath = nativeLibraryPath + "/macos";
        }
        return nativeLibraryPath;
    }

    private static boolean extractAndLoadLibraryFile(String libFolderForCurrentOS, String libraryFileName, String targetFolder, Class jniClass) {
        String nativeLibraryFilePath = libFolderForCurrentOS + "/" + libraryFileName;
        String extractedLibFileName = libraryFileName;
        File extractedLibFile = new File(targetFolder, extractedLibFileName);

        try {
            if (extractedLibFile.exists()) {
                // test md5sum value
                String md5sum1 = Utils.md5sum(jniClass.getResourceAsStream(nativeLibraryFilePath));
                String md5sum2 = Utils.md5sum(new FileInputStream(extractedLibFile));

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
            InputStream reader = jniClass.getResourceAsStream(nativeLibraryFilePath);
            extract(extractedLibFile, reader);

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

    /**
     * Registers a native library using JNA:
     * 1. First extracts embed lib from resources to Java temp folder
     * 2. Sets jna.library.path to match Java temp folder
     * 3. Registers lib using Native.register
     *
     * @param jniClass a class which holds native methods
     * @param libName
     * @return success
     * @throws IOException
     */
    public static boolean registerJNA(Class jniClass, String libName) throws IOException {
        String systemLibName = System.mapLibraryName(libName);
        String extractedPath = extractFromResources(packageName(jniClass), systemLibName, jniClass);
        System.setProperty("jna.library.path", Paths.get(extractedPath).getParent().toString());
        Native.register(jniClass, libName);
        return true;
    }

    /**
     * Extracts libFile from resources to temp dir
     *
     * @return extracted path
     */
    private static String extractFromResources(String packageName, String libraryFileName, Class jniClass) throws IOException {
        String tempFolder = tempFolderPath();
        String targetFolder = tempFolder;

        String nativeLibraryFilePath = libFolderForCurrentOs(packageName) + "/" + libraryFileName;
        String extractedLibFileName = libraryFileName;
        File extractedLibFile = new File(targetFolder, extractedLibFileName);

        if (extractedLibFile.exists()) {
            // test md5sum value
            String md5sum1 = Utils.md5sum(jniClass.getResourceAsStream(nativeLibraryFilePath));
            String md5sum2 = Utils.md5sum(new FileInputStream(extractedLibFile));

            if (md5sum1.equals(md5sum2)) {
                return extractedLibFile.getAbsolutePath();
            } else {
                // remove old native library file
                boolean deletionSucceeded = extractedLibFile.delete();
                if (!deletionSucceeded) {
                    throw new IOException("failed to remove existing native library file: " + extractedLibFile.getAbsolutePath());
                }
            }
        }

        // Extract file into the current directory
        InputStream reader = jniClass.getResourceAsStream(nativeLibraryFilePath);

        return extract(extractedLibFile, reader);
    }

    /**
     * Extracts a library from resources
     * @param extractedLibFile
     * @param reader
     * @return extracted path
     * @throws IOException
     */
    private static String extract(File extractedLibFile, InputStream reader) throws IOException {
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

        return extractedLibFile.getAbsolutePath();
    }

    private static String tempFolderPath() {
        return new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
    }

    private static String packageName(Class jniClass) {
        String packageName = jniClass.getPackage().getName();
        return packageName.substring(packageName.lastIndexOf(".") + 1);
    }
}