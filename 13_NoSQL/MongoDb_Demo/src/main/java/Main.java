import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class Main {

    static final String URL = "127.0.0.1";
    static final int PORT = 27017;
    static final String DB_NAME = "skill-mongo";
    static final String CSV_FILE = "students.csv";

    public static void main(String[] args) {
        String line;
        String[] values;
        List<String> courses;
        MongoClient mongoClient = new MongoClient( URL, PORT );
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> collection = database.getCollection("students");
        collection.drop();
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

        System.out.println("Total Students : " + collection.countDocuments());
        printSeparateLine();

        BasicDBObject query = new BasicDBObject();
        query.put("age", new BasicDBObject("$gt", "40"));
        List<Document> documents = collection.find(query).into(new ArrayList<>());
        System.out.println("Total Students with ages 40+ : " + documents.size());
        printSeparateLine();

        documents = collection.find().sort(ascending("age")).into(new ArrayList<>());
        System.out.println("Total Students with MIN ages : " + countAndPrintTargetAge(documents));
        printSeparateLine();

        documents = collection.find().sort(descending("age")).into(new ArrayList<>());
        System.out.println("Total Students with MAX ages : " + countAndPrintTargetAge(documents));
        printSeparateLine();
    }

    static void printSeparateLine() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n");
    }

    static int countAndPrintTargetAge(List<Document> documents) {
        Object targetAge = documents.get(0).get("age");
        int count = 0;
        for (Document document : documents) {
            if (document.get("age").equals(targetAge)) {
                count++;
                System.out.println("Name :\t" + document.get("name")
                        + " ;  age :\t" + document.get("age")
                        + " ;  courses :\t" + document.get("courses"));
            } else break;
        }
        return count;
    }

    static List<String> getCurrentCourses(String line) {
        int firstChar = line.indexOf('"');
        int lastChar = line.lastIndexOf('"');
        String str = line.substring(firstChar + 1, lastChar).trim();
        return Arrays.asList(str.split("\\s*,\\s*"));
    }
}