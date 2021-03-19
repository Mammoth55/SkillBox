import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ParserCSVtoMongoDbTest {

    private MyMongoDbCollection myCollection;

    @Before
    public void setUp() {
        myCollection = new MyMongoDbCollection(Main.MONGO_URL, Main.MONGO_PORT, Main.DATABASE_NAME, Main.COLLECTION_NAME);
        ParserCSVtoMongoDB parser = new ParserCSVtoMongoDB(Main.CSV_FILE, myCollection.getCollection());
    }

    @Test
    public void whenParsingFullSize() {
        long actual = myCollection.getCollection().countDocuments();
        assertEquals(100, actual);
    }

    @Test
    public void whenSortAndFindMin() {
        long actual = myCollection.getMinAgesAndPrint();
        assertEquals(3, actual);
    }

    @Test
    public void whenSortAndFindMax() {
        long actual = myCollection.getMaxAgesAndPrint();
        assertEquals(2, actual);
    }
}