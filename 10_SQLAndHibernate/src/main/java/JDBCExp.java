import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExp {

    private static final String URL = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "3141592653589+Pi";

    public static void main(String[] args) {
        try {
            try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT course_name, COUNT(*) "
                         + "/ (max(MONTH(subscription_date)) - min(MONTH(subscription_date))) as SALE_PER_MONTH "
                         + "from PurchaseList "
                         + "where YEAR(subscription_date) = 2018 "
                         + "group by course_name;")) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("course_name") + "\t"
                            + resultSet.getString("SALE_PER_MONTH"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}