import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.assertEquals;

public class MainTest {

    private static final String URL = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "3141592653589+Pi";
    private static final String FILE_NAME = "src/main/resources/queries.sql";
    private static Statement statement;

    @Before
    public void setUp() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        statement = connection.createStatement();
    }

    @Test
    public void testAllEntries() throws SQLException {
        String query = "SELECT * FROM animals;";
        int actual = Main.getResultByQuery(statement,query);
        assertEquals(16, actual);
    }

    @Test
    public void testSomeEntries() throws SQLException {
        String query = "SELECT * FROM animals where height='маленькое';";
        int actual = Main.getResultByQuery(statement,query);
        assertEquals(4, actual);
    }
}