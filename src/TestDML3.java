import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 上一个类中，构建sql语句十分麻烦；
 * 为此有了新的方法构建sql；
 */
public class TestDML3 {

    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //new  oracle.jdbc.driver.OracleDriver();
        //自动注册；
        Connection connection = null;
        //Statement statement = null;
        PreparedStatement preparedStatement = null;
        String sql_num;
        String sql_dname;
        String sql_loc;
        sql_num = args[0];
        sql_dname = args[1];
        sql_loc = args[2];
        int num = Integer.parseInt(sql_num);
        //String sql = "UPDATE DEPT SET LOC = 'BEI JING' WHERE DEPTNO = 98";
        //System.out.println(sql_num+" "+sql_dname+" "+sql_loc);
        //String sql = "INSERT INTO DEPT VALUES ("+num+","+"'"+ sql_dname+"'"+","+"'"+sql_loc+"'"+")";
        //System.out.println(sql);
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "320823");
            //statement = connection.createStatement();
            preparedStatement = connection.prepareCall("INSERT INTO DEPT VALUES (?,?,?)");
            preparedStatement.setInt(1, num);
            preparedStatement.setString(2, sql_dname);
            preparedStatement.setString(3, sql_loc);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
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
