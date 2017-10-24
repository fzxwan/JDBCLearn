import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //new  oracle.jdbc.driver.OracleDriver();
        //自动注册；
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "320823");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM EMP");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("mgr"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
