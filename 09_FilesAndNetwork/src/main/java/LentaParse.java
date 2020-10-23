import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;

public class LentaParse {

    private static final String HTML_PATH = "https://lenta.ru/";
    private static final String FOLDER_PATH = "target\\receivedImages\\";

    public static void main(String[] args) {
        String src;
        System.out.println("Download from  " + HTML_PATH);
        try {
            Document doc  = Jsoup.connect(HTML_PATH).get();
            Elements metaElements = doc.getElementsByTag("img");
            metaElements.remove(metaElements.size() - 2);
            metaElements.remove(metaElements.size() - 1);
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
        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");
        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }
        String name = src.substring(indexname + 1, src.length());
        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(FOLDER_PATH + name));
        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        System.out.println(name + "   Download success.");
        out.close();
        in.close();
    }
}