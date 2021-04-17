import java.sql.*;
import java.text.SimpleDateFormat;

public class DBConnection {

    private static Connection connection;
    private final static String dbName = "skillbox";
    private final static String dbUser = "root";
    private final static String dbPass = "3141592653589+Pi";
    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC", dbUser, dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        if (insertQuery.length() > 0) {
            String sql = "INSERT INTO voter_count(name,birthDate,`count`) VALUES" + insertQuery;
            DBConnection.getConnection().createStatement().execute(sql);
        }
    }

    public static void countVoter(Voter voter) throws SQLException {
        String birthDay = new SimpleDateFormat("yyyy.MM.dd").format(voter.getBirthDay()).
                replace('.', '-');
        insertQuery.append((insertQuery.length() == 0 ? "" : ",") + "('"
                + voter.getName() + "','" + birthDay + "',1)");
        if (insertQuery.length() > 2_097_000) {
            executeMultiInsert();
            insertQuery = new StringBuilder();
        }
    }

    public static int countVoterByName(String name) throws SQLException {
        String sql = "SELECT count(*) AS C FROM voter_count where name='" + name + "'";
        int count = 0;
        try (ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql)) {
            while (rs.next()) {
                count = rs.getInt("C");
            }
        }
        return count;
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, count(v.count) as c FROM voter_count as v group by name order by c desc";
        try (ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql)) {
            while (rs.next()) {
                int count = rs.getInt("c");
                if (count > 1) {
                    System.out.println("\t" + rs.getString("name") + " (" +
                            rs.getString("birthDate") + ") - " + count);
                } else break;
            }
        }
    }
}