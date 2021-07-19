package Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class hotelsql {
    private static Statement ste = null;
    private static Connection dbConn = null;
    static{
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

    }
    //防止实例化hotelsql对象
    private hotelsql(){
    }
    //执行对数据库更改的sql命令，并返回更改所影响的行数
    public static int executeUpdate(String sql) {
        int i = 0 ;
        try {
            i = ste.executeUpdate(sql) ;
            dbConn.commit();
        }catch(Exception e) {
            e.printStackTrace() ;
            System.out.println("executeUpdate()有错");
        }//End try
        return i ;
    }
    //利用事务的模式以updateCode中的sql语句对数据库进行更新
    public static int runTransaction (String updateCode[]) {
        int ok = 0, i = 0;
        int row = updateCode.length;		//更新语句的数量
        System.out.println("row="+row);
        try {
            for (i = 0; i < row; i++) {
                System.out.println(updateCode[i]);
                ok = ste.executeUpdate (updateCode[i]);		//执行SQL语句
                System.out.println(ok);
                if(ok == 0) {				//如果不成功，则跳出循环
                    System.out.println ("hotelsql.runTransaction(): updateCode[" + i + "] 失败" + ok);
                    break;
                }
                System.out.println ("hotelsql.runTransaction(): updateCode[" + i + "] 成功 " + ok);
            }
            //根据变量 ok 判断上面循环是否正常运行完毕
            if(ok == 0) {
                dbConn.rollback ();		//(ok == 0)表示更新过程中出错，回滚数据
                System.out.println ("hotelsql.runTransaction(): Update data false, rollback");
            }
            else {
                dbConn.commit ();			//(ok != 0)基本上是所有SQL语句运行成功, 则提交给数据库
                System.out.println ("hotelsql.runTransaction(): Update finish");
            }
        }
        catch (Exception ex) {
            System.out.println ("hotelsql.runTransaction(): Update false ...");
        }
        return i;
    }
    //执行对数据库的select查询功能，并返回查询所得到的结果
    public static ResultSet executeQuery(String sql) {
        System.out.println ("Query SQL : " + sql);
        ResultSet rs = null ;
        try {
            rs = ste.executeQuery(sql) ;
        }catch(Exception e) {
            e.printStackTrace() ;
        }//End try
        return rs ;
    }
    //获得指定结果集的记录数量
    public static int recCount(ResultSet rrs) {
        int i = 0;
        try {
            if(rrs.getRow() != 0)
                rrs.beforeFirst();
            //while用于计算rs的记录条数
            while(rrs.next())
                i++;
            rrs.beforeFirst();
        }catch(Exception ex) {
            ex.printStackTrace();
        }//End try
        return i;
    }
    //通过服务器当前的时间获得一个主键
    public static long getPrimaryKey() {
        long pk = 0;

        try {
            //获得服务器时间
            ResultSet rs = executeQuery("select getdate()");
            rs.next();
            pk = rs.getTimestamp(1).getTime();
        }
        catch (Exception ex) {
            System.out.println ("hotelsql.getPrimaryKey (): false");
        }
        return pk;
    }
    //按SQL语句从数据库中获得数据，添加到fdtm中(也可以说JTable中)
    public static void initDTM (DefaultTableModel fdtm, String sqlCode) {
        try {
            ResultSet rs = executeQuery( sqlCode );	//获得结果集
            int row = recCount( rs );				//获得结果集中有几行数据
            ResultSetMetaData rsm =rs.getMetaData();	//获得列集
            int col = rsm.getColumnCount();		//获得列的个数
            String colName[] = new String[col];
            //取结果集中的表头名称, 放在colName数组中
            for (int i = 0; i < col; i++) {
                colName[i] = rsm.getColumnName( i + 1 );
            }//End for
            rs.beforeFirst();
            String data[][] = new String[row][col];
            //取结果集中的数据, 放在data数组中
            for (int i = 0; i < row; i++) {
                rs.next();
                for (int j = 0; j < col; j++) {
                    data[i][j] = rs.getString (j + 1);
                    //System.out.println (data[i][j]);
                }
            }//End for
            fdtm.setDataVector (data, colName);
        }
        catch (Exception ex) {
            System.out.println ("hotelsql.initDTM (): false");
        }//End try
    }

}
