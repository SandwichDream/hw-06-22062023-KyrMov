package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileService {

    public static long copyFile(File fileIn, File fileOut) throws IOException {

        try (InputStream is = new FileInputStream(fileIn); OutputStream os = new FileOutputStream(fileOut)) {
            return is.transferTo(os);
        }
    }

    public static long copyFolder(File folderIn, File folderOut, long fileMaxSize) throws IOException {
        File[] files = folderIn.listFiles();
        long fileCopy = 0L;
        for (File file : files) {
            if (file.isFile() && file.length() <= fileMaxSize) {
                File fileOut = new File(folderOut, file.getName());
                copyFile(file, fileOut);
                fileCopy += 1;
            }
        }
        return fileCopy;
    }

    public static long copyFolder(File folderIn, File folderOut, long fileMaxSize, String extension)
            throws IOException {
        File[] files = folderIn.listFiles();
        long fileCopy = 0L;

        for (File file : files) {
            String fileExtension = "";
            char[] spellFileName = file.getName().toCharArray();

            for (int i = spellFileName.length - 1; i >= 0; i--) {
                fileExtension = spellFileName[i] + fileExtension;
                if (spellFileName[i] == '.') {
                    break;
                }
            }
            if (file.isFile() && file.length() <= fileMaxSize && fileExtension.equals(extension)) {
                File fileOut = new File(folderOut, file.getName());
                copyFile(file, fileOut);
                fileCopy += 1;
            }
        }
        return fileCopy;
    }
}