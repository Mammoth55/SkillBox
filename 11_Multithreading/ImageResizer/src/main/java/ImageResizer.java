import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageResizer implements Runnable {

    private File[] files;
    private int newWidth;
    private String dstFolder;
    private long startTime;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long startTime) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));
                BufferedImage newImage = Scalr.resize(image, newWidth, newHeight, null);
                String fileName = file.getName();
                File newFile = new File(dstFolder + "/" + fileName);
                String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                ImageIO.write(newImage, fileExtension, newFile);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " done !\nDuration: "
                + (System.currentTimeMillis() - startTime) + " ms");
    }
}