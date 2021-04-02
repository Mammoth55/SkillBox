import com.mongodb.WriteResult;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MyMongoDbStorageTest {

    private MyMongoDbStorage storage;

    @Before
    public void setUp() {
        storage = new MyMongoDbStorage(Main.MONGO_URL, Main.MONGO_PORT, Main.DATABASE_NAME);
    }

    @Test
    public void whenAddShopThenGetAndDelete() {
        String expected = "IKEA";
        storage.addNewShop(expected);
        String actual = storage.getShopByName(expected).getName();
        assertEquals(expected, actual);
        WriteResult result = storage.deleteShopByName(expected);
        Shop shop = storage.getShopByName(expected);
        assertNull(shop);
    }

    @Test
    public void whenAddProductThenGetAndDelete() {
        String expected = "SAAB9000";
        storage.addNewProduct(expected, 9999);
        String actual = storage.getProductByName(expected).getName();
        assertEquals(expected, actual);
        WriteResult result = storage.deleteProductByName(expected);
        Product product = storage.getProductByName(expected);
        assertNull(product);
    }
}