import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class SiteMap implements Node {

    private static final String HTML_PATH = "https://skillbox.ru/";
    private static final String MY_SITE1 =
            HTML_PATH.substring(HTML_PATH.indexOf("//"), HTML_PATH.indexOf(".")); // use "//skillbox";
    private static final String MY_SITE2 =
            "." + HTML_PATH.substring(HTML_PATH.indexOf("//") + 2, HTML_PATH.indexOf(".")); // use ".skillbox";
    private static final String FILE_PATH = "src/main/resources/SiteMap.txt";
    private static final Map<String, Boolean> uniqueURL = new ConcurrentHashMap<>();
    private static int count;
    private final String currentURL;

    public SiteMap(String currentURL) {
        this.currentURL = currentURL;
    }

    public static void main(String[] args) {
        Node root = new SiteMap(HTML_PATH);
        System.out.println("Scanning site : " + HTML_PATH);
        try {
            new ForkJoinPool().invoke(new NodeUrlCollector(root));
            System.out.println("Scanning complete.");
            fileSave(FILE_PATH);
        } catch (Exception ex) {
            System.out.println("EXEPTION : " + ex.getMessage());
        }
    }

    public static Boolean putUniqueURL(String url) {
        String urlWithoutHTTP = url.substring(url.indexOf(":") + 3);
        Boolean back = uniqueURL.putIfAbsent(urlWithoutHTTP, true);
        if (back == null) {
            System.out.print(++count + "\r");
        }
        return back;
    }

    private boolean isRealURL(String url) {
        return (url.contains(MY_SITE1) || url.contains(MY_SITE2))
                && !url.contains("#") && !url.contains("?")
                && !url.contains("@") && url.endsWith("/");
    }

    public static void fileSave(String filePath) {
        Set<String> urlSet = new TreeSet<>(uniqueURL.keySet());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String url : urlSet) {
                long tabCount = url.chars().filter(ch -> ch == '/').count() - 1;
                for (int i = 0; i < tabCount; i++) {
                    writer.write('\t');
                }
                writer.write(url);
                writer.write('\n');
            }
        } catch (Exception ex) {
            System.out.println("EXEPTION : " + ex.getMessage());
        }
        System.out.println("File saved.");
    }

    @Override
    public Collection<Node> getChildren() {
        Collection<Node> childrenList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(this.getValue()).get();
            Elements links = doc.select("a");
            if (links.isEmpty()) {
                return null;
            }
            childrenList = links.stream()
                    .map((link) -> link.attr("abs:href"))
                    .filter((this::isRealURL))
                    .map(SiteMap::new)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("EXEPTION : " + ex.getMessage());
        }
        return childrenList;
    }

    @Override
    public String getValue() {
        return currentURL;
    }
}