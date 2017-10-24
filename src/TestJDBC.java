import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        //new  oracle.jdbc.driver.OracleDriver();
        //自动注册；

            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "320823");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMP");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("JOB"));
            }



    }
}
