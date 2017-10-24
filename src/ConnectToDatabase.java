import java.sql.*;

/**
 * 温习JDBC的六个流程；
 */
public class ConnectToDatabase {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //第一步，获得一个driver;
            Class.forName("oracle.jdbc.OracleDriver");
            //第二步，获得连接；
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "320823");
            //第三步，获得statement对象；
            statement = connection.createStatement();
            String sql = "select * from emp";
            //第五步，执行sql;
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int cnt = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= cnt; i++) {
                    System.out.print(resultSet.getString(i));
                    System.out.print(" ");
                }
                System.out.println();
            }
            //上面执行了查询语句，下面执行一个update语句;
            connection.setAutoCommit(false);
            statement.executeUpdate("INSERT INTO DEPT VALUES (77,'LOL','Avatar')");
            //下面学习下PreparedStatement的使用；
            String sql3 = "INSERT INTO Dept VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareCall(sql3);
            preparedStatement.setInt(1, 36);
            preparedStatement.setString(2, "Wow");
            preparedStatement.setString(3, "MOM");
            preparedStatement.execute();
            //下面学习下批处理；
            preparedStatement.setInt(1, 22);
            preparedStatement.setString(2, "Wow");
            preparedStatement.setString(3, "MOM");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 23);
            preparedStatement.setString(2, "Wow");
            preparedStatement.setString(3, "MOM");
            preparedStatement.addBatch();

            preparedStatement.setInt(1, 24);
            preparedStatement.setString(2, "Wow");
            preparedStatement.setString(3, "MOM");
            preparedStatement.addBatch();

            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            //第六步finally善后，释放连接资源，养成习惯；
        } finally {
            connection.rollback();
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
