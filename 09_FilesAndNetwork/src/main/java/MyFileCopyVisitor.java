import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class MyFileCopyVisitor extends SimpleFileVisitor<Path> {

    private Path source, destination;

    public MyFileCopyVisitor(Path s, Path d) {
        source = s;
        destination = d;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        Path newDest = destination.resolve(source.relativize(path));
        try {
            Files.copy(path, newDest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        Path newDest = destination.resolve(source.relativize(path));
        try {
            Files.copy(path, newDest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }
}