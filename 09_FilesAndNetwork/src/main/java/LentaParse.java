import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class LentaParse {

    private static final String HTML_PATH = "https://lenta.ru/";
    private static final String FOLDER_PATH = "target/receivedImages/";

    public static void main(String[] args) {
        System.out.println("Download from  " + HTML_PATH);
        Path path = Paths.get(FOLDER_PATH);
        try {
            if (Files.notExists(path)) {
                Files.createDirectory(path);
            }
            Document doc  = Jsoup.connect(HTML_PATH).maxBodySize(0).get();
            Elements metaElements = doc.getElementsByTag("img");
            for (Element metaElement : metaElements) {
                getImage(metaElement.absUrl("src"));
            }
            System.out.println("Done.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void getImage(String src) throws IOException {
        String name = src.substring(src.lastIndexOf("/") + 1);
        if (name.endsWith(".jpg")) {
            URL url = new URL(src);
            try (InputStream in = url.openStream();
                 OutputStream out = new BufferedOutputStream(
                         new FileOutputStream(FOLDER_PATH + name))) {
                in.transferTo(out);
                System.out.println(name + "   Download success.");
            }
        }
    }
}