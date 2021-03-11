import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "3141592653589+Pi";
    private static final String FILE_NAME = "src/main/resources/queries.sql";

    public static void main(String[] args) {
        String query;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILE_NAME), StandardCharsets.UTF_8));
             Connection connection = DriverManager.getConnection(URL, USER, PASS);
             Statement statement = connection.createStatement()) {
            while ((query = reader.readLine()) != null) {
                System.out.println("Total entries found : " + getResultByQuery(statement,query));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static int getResultByQuery(Statement statement, String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        int counter = 0;
        System.out.println("\r\n" + "QUERY loaded : " + query);
        System.out.println("===============================================");
        while (resultSet.next()) {
            System.out.println(++counter + ". "
                    + resultSet.getString("name") + "\t"
                    + resultSet.getString("type") + "\t"
                    + resultSet.getString("weight") + "\t"
                    + resultSet.getString("height"));
        }
        System.out.println("===============================================");
        return counter;
    }
}