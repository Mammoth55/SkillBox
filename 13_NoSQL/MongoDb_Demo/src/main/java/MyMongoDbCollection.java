import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class MyMongoDbCollection {

    private final MongoCollection<Document> collection;

    public MyMongoDbCollection(String url, int port, String db, String name) {
        MongoClient mongoClient = new MongoClient(url, port);
        MongoDatabase database = mongoClient.getDatabase(db);
        MongoCollection<Document> collection = database.getCollection(name);
        collection.drop();
        this.collection = collection;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public long getCollectionSize() {
        return collection.countDocuments();
    }

    public long getAgesGreaterThanValue(int value) {
        return collection.find(gt("age", String.valueOf(value))).into(new ArrayList<>()).size();
    }

    public long getMinAgesAndPrint() {
        return countAndPrintTargetAge(collection.find().sort(ascending("age")).into(new ArrayList<>()));
    }

    public long getMaxAgesAndPrint() {
        return countAndPrintTargetAge(collection.find().sort(descending("age")).into(new ArrayList<>()));
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
}