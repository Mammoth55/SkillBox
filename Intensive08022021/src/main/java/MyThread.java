import com.dropbox.core.v2.DbxClientV2;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {

    private DbxClientV2 client;
    private BufferedImage image;

    public MyThread(DbxClientV2 client, BufferedImage image) {
        this.client = client;
        this.image = image;
    }

    @Override
    public void run() {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", os);
            InputStream in = new ByteArrayInputStream(os.toByteArray());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentFilePath = "/" + formatter.format(new Date()) + ".png";
            client.files().uploadBuilder(currentFilePath).uploadAndFinish(in);
            System.out.println(currentFilePath + " is uploaded.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}