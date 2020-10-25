import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;

public class LentaParse {

    private static final String HTML_PATH = "https://lenta.ru/";
    private static final String FOLDER_PATH = "target/receivedImages/";

    public static void main(String[] args) {
        String src;
        System.out.println("Download from  " + HTML_PATH);
        try {
            Document doc  = Jsoup.connect(HTML_PATH).maxBodySize(0).get();
            Elements metaElements = doc.getElementsByTag("img");
            for (Element metaElement : metaElements) {
                src = metaElement.absUrl("src");
                getImage(src);
            }
            System.out.println("Done.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void getImage(String src) throws IOException {
        // Extract the name of the image from the src attribute
        int indexName = src.lastIndexOf("/");
        if (indexName == src.length()) {
            src = src.substring(1, indexName);
        }
        String name = src.substring(indexName + 1, src.length());
        if (name.endsWith(".jpg")) {
            // Work with .jpg only !
            URL url = new URL(src); // Open a URL Stream
            InputStream in = url.openStream();
            try (OutputStream out = new BufferedOutputStream(
                    new FileOutputStream(FOLDER_PATH + name))) {
                for (int b; (b = in.read()) != -1;) {
                    out.write(b);
                }
                System.out.println(name + "   Download success.");
                out.close();
                in.close();
            }
        }
    }
}