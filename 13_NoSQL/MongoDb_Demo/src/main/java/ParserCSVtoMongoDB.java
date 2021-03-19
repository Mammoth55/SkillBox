import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ParserCSVtoMongoDB {

    private final String CSV_FILE;
    private final MongoCollection<Document> collection;

    public ParserCSVtoMongoDB(String CSV_FILE, MongoCollection<Document> collection) {
        this.CSV_FILE = CSV_FILE;
        String line;
        String[] values;
        List<String> courses;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(CSV_FILE), StandardCharsets.UTF_8))) {
            while ((line = reader.readLine()) != null) {
                values = line.trim().split("\\s*,\\s*", 3);
                courses = getCurrentCourses(values[2]);
                Document currentDocument = new Document()
                        .append("name", values[0])
                        .append("age", values[1])
                        .append("courses", courses);
                collection.insertOne(currentDocument);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.collection = collection;
    }

    static List<String> getCurrentCourses(String line) {
        int firstChar = line.indexOf('"');
        int lastChar = line.lastIndexOf('"');
        String str = line.substring(firstChar + 1, lastChar).trim();
        return Arrays.asList(str.split("\\s*,\\s*"));
    }
}