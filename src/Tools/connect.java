package Tools;

import java.sql.*;

public class connect {
    private static Statement ste = null;
    private static Connection dbConn = null;
    public static Connection getConnection() {
        ResultSet rs =null;
        String result = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/hotel?useUnicode=true&characterEncoding=utf-8";
        String user = "root";
        String password = "";
        try {
            Class.forName(driver);
// System.out.println("找到驱动了");
            try {
                dbConn = DriverManager.getConnection(url, user, password);
// System.out.println("数据库连接成功！");
                ste = dbConn.createStatement();
            } catch (SQLException e) {
                System.out.println("数据库连接失败！");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();

        }
        return dbConn;
    }
    private connect(){};
}
