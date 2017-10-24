import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 学习调用存储过程；
 */
public class TestJDBCProcedure {
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
        String sql_num;
        String sql_dname;
        String sql_loc;
        sql_num = args[0];
        sql_dname = args[1];
        sql_loc = args[2];
        int num = Integer.parseInt(sql_num);
        //String sql = "UPDATE DEPT SET LOC = 'BEI JING' WHERE DEPTNO = 98";
        //System.out.println(sql_num+" "+sql_dname+" "+sql_loc);
        String sql = "INSERT INTO DEPT VALUES (" + num + "," + "'" + sql_dname + "'" + "," + "'" + sql_loc + "'" + ")";
        System.out.println(sql);
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "320823");
            statement = connection.createStatement();
            statement.executeUpdate(sql);
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
