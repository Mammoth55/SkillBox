import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MetroMSK {

    private static final String HTML_PATH = "https://www.moscowmap.ru/metro.html#lines";
    private static final String JSON_PATH = "src/main/resources/map.json";
    private static List<Line> lines = new ArrayList<>();
    private static List<Station> stations = new ArrayList<>();
    private static List<Connection> connections = new ArrayList<>();
    private static Map<String, List<String>> stationsOut = new HashMap<>();
    private static List<List<Station>> connectionsOut = new ArrayList<>();

    public static void main(String[] args) {
        try {
            htmlParse(HTML_PATH);
            jsonSave(JSON_PATH);
            jsonLoad(JSON_PATH);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void jsonSave(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
//      блок stations
        JSONObject obj = new JSONObject();
        List<String> list = new ArrayList<>();
        String lineNumber = "";
        for (Station station : stations) {
            if (!station.getLineNumber().equals(lineNumber)) {
                if (lineNumber.length() == 0) {
                    lineNumber = station.getLineNumber();
                } else {
                    stationsOut.put(lineNumber, list);
                    list = new ArrayList<>();
                    lineNumber = station.getLineNumber();
                }
            }
            list.add(station.getName());
        }
        stationsOut.put(lineNumber, list);
        obj.put("stations", stationsOut);
//      блок connections
        for (Connection connection : connections) {
            List<Station> listOf2 = new ArrayList<>(2);
            listOf2.add(connection.getFrom());
            listOf2.add(connection.getTo());
            connectionsOut.add(listOf2);
        }
        obj.put("connections", connectionsOut);
//      блок lines
        obj.put("lines", lines);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void jsonLoad(String filePath) throws Exception {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
//      блок stations
            JSONObject jsonObject = (JSONObject) obj;
            stationsOut = new LinkedHashMap<>();
            stationsOut = (Map<String, List<String>>) jsonObject.get("stations");
            for (String str : stationsOut.keySet()) {
                System.out.println("Line " + str + " => " + stationsOut.get(str).size() + " stations.");
            }
//      блок connections
            connectionsOut = new ArrayList<>();
            connectionsOut = (List<List<Station>>) jsonObject.get("connections");
            System.out.println("Total found " + connectionsOut.size() + " connections.");
//      блок lines
            lines = new ArrayList<>();
            lines = (List<Line>) jsonObject.get("lines");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void htmlParse(String url) throws Exception {
        System.out.println("Download from  " + url);
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Elements metaElements = doc.select("span[class]");
        String lineNumber = "";
        for (Element metaElement : metaElements) {
            String str = metaElement.toString();
            if (!str.contains("list-header")) {
                if (str.contains("\"name")) {
                    String stationName = metaElement.text();
                    stations.add(new Station(lineNumber, stationName));
                } else {
                    if (str.contains("ln-")) {
                        int i = str.indexOf("ln-");
                        int j = str.indexOf("\"", i);
                        String numberLineTo = str.substring(i + 3, j);
                        i = str.indexOf("«");
                        j = str.indexOf("»", i);
                        String nameStationTo = str.substring(i + 1, j);
                        connections.add(new Connection(stations.get(stations.size() - 1),
                                new Station(numberLineTo, nameStationTo)));
                    }
                }
            } else {
                lineNumber = metaElement.attr("data-line");
                String lineName = metaElement.text();
                lines.add(new Line(lineNumber, lineName));
            }
        }
        System.out.println("Done.");
    }
}