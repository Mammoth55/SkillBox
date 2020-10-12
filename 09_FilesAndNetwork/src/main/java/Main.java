import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static long getFolderSize(String path) throws IOException {
        Path folder = Paths.get(path);
        return Files.walk(folder)
                .map(Path::toFile)
                .filter(File::isFile)
                .mapToLong(File::length)
                .sum();
    }

    private static String getDirectoryPath(String message) {
        String line;
        File path;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(message);
            line = scanner.nextLine().trim();
            path = new File(line);
        } while (!path.exists() || !path.isDirectory());
        return line;
    }

    public static void main(String[] args) {
        String path;
        while (true) {
            path = getDirectoryPath("Введите путь к целевой папке (D:\\1 for example) :");
            try {
                long size = getFolderSize(path);
                System.out.println("Объем целевой папки " + path + " составляет " + (size / 1024) + " Kbytes.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}