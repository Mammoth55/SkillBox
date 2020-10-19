import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DirectoryCopyWithVisitor {

    private static String getDirectoryPath(String message) {
        String line;
        Path path;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(message);
            line = scanner.nextLine().trim();
            path = Paths.get(line);
        } while (!Files.exists(path, LinkOption.NOFOLLOW_LINKS) || !Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS));
        return line;
    }

    public static void main(String[] args) {
        Path pathSource = Paths.get(getDirectoryPath("Введите путь к копируемой папке (C:\\From for example) :"));
        Path pathDestination = Paths.get(getDirectoryPath("Введите путь к папке для копирования (D:\\To for example) :"));
        try {
            Files.walkFileTree(pathSource, new MyFileCopyVisitor(pathSource, pathDestination));
            System.out.println("Files copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}