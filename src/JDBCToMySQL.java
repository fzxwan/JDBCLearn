import java.sql.*;

public class JDBCToMySQL {


    public static void main(String[] args) {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/bbs";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "320823");
            tree(connection, 0, 0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    public static void tree(Connection connection, int id, int level) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet;
        StringBuffer strPre = new StringBuffer("");
        for (int i = 0; i < level; i++) {
            strPre.append("    ");
        }
        String sql = "select * from article where pid = " + id;
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(strPre + resultSet.getString("cont"));

            tree(connection, resultSet.getInt("id"), level + 1);
        }
    }
}
