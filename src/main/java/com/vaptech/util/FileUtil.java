package com.vaptech.util;

import java.io.*;

/**
 * Utility class that handles file I/O actions
 *
 * @author Víctor Andrés Pedraza León
 * @since 1.8
 */
public final class FileUtil {

    private FileUtil(){}

    public static byte[] readBytesFile(File file) {
        byte[] bytes = new byte[(int)file.length()];
        try(InputStream inputStream = new FileInputStream(file)){
            inputStream.read(bytes);
            return bytes;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String readContentString(File file) {
        StringBuilder builder = new StringBuilder();
        try(FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while((line = bufferedReader.readLine()) != null)
                builder.append(line).append("\n");
            return builder.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static byte[] stringBytes(String str) {
        return str != null ? str.getBytes() : new byte[0];
    }

    public static boolean writeBytesFile(File file, byte[] bytes){
        try(OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(bytes);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
