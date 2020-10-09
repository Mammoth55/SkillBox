import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class DirectoryCopy {

    private static final int MIN_PATH_LENGTH = 4;

    public static void main(String[] args) {
        String pathFrom, pathTo;
        try {
            while (true) {
                pathFrom = getPath("Введите путь к копируемой папке (C:\\From for example) :");
                pathTo = getPath("Введите путь к папке для копирования (D:\\To for example) :");
                copyDir(pathFrom, pathTo);
            }
        } catch (Exception ex) {
                ex.printStackTrace();
        }
    }

    private static String getPath(String message) {
        String line;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(message);
            line = scanner.nextLine().trim();
        } while (line.length() < MIN_PATH_LENGTH);
        return line;
    }

    private static void copyDir(String src, String dst) throws IOException {
        System.out.println("Копируем каталог (если существует) : " + src);
        File srcFile = new File(src);
        File dstFile = new File(dst);
        if (srcFile.exists() && srcFile.isDirectory() && !dstFile.exists()) {
            dstFile.mkdir();
            File nextSrcFile;
            String nextSrcFilename, nextDstFilename;
            for (String filename : srcFile.list()) {
                nextSrcFilename = srcFile.getAbsolutePath() + File.separator + filename;
                nextDstFilename = dstFile.getAbsolutePath() + File.separator + filename;
                nextSrcFile = new File(nextSrcFilename);
                if (nextSrcFile.isDirectory()) {
                    copyDir(nextSrcFilename, nextDstFilename);
                }
                else {
                    copyFile(nextSrcFilename, nextDstFilename);
                }
            }
        }
    }

    private static void copyFile(String src, String dst) throws IOException {
        System.out.println("Копируем файл : " + src);
        File srcFile = new File(src);
        File dstFile = new File(dst);
        if (srcFile.exists() && srcFile.isFile() && !dstFile.exists()) {
            Files.copy(srcFile.toPath(), dstFile.toPath());
        }
    }
}