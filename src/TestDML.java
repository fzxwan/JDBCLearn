import java.sql.*;

public class TestDML {
    public static void main(String[] args) {
        try {
            //Class.forName("oracle.jdbc.OracleDriver");
            //new oracle.jdbc.OracleDriver();
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //new  oracle.jdbc.driver.OracleDriver();
        //自动注册；
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM EMP";
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "320823");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(sql);
            /**
             * 更新一行；
             */
            resultSet.next();
            resultSet.updateString(1, "Aibilim");
            resultSet.updateRow();
            /**
             * 插入新行；
             */
            resultSet.moveToInsertRow();
            resultSet.updateString(1, "Dalaha");
            resultSet.updateString(2, "clerk");
            resultSet.updateInt(7, 20);
            resultSet.insertRow();

            resultSet.moveToCurrentRow();

            resultSet.absolute(5);
            resultSet.deleteRow();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
