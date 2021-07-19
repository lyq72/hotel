package Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public class SimpleTools {

    /**
     * 操作结果：自定义一个JavaFX的对话框
     *
     * @param alterType 对话框类型
     * @param title     对话框标题
     * @param header    对话框头信息
     * @param message   对话框显示内容
     * @return boolean 如果点击了”确定“那么就返回true，否则返回false
     */
    public boolean informationDialog(Alert.AlertType alterType, String title, String header, String message) {
        // 按钮部分可以使用预设的也可以像这样自己 new 一个
        Alert alert = new Alert(alterType, message, new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE), new ButtonType("确定", ButtonBar.ButtonData.YES));
        // 设置对话框的标题
        alert.setTitle(title);
        alert.setHeaderText(header);
        // showAndWait() 将在对话框消失以前不会执行之后的代码
        Optional<ButtonType> buttonType = alert.showAndWait();
        // 根据点击结果返回
        if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
            return true;// 如果点击了“确定”就返回true
        } else {
            return false;
        }
    }

    public static final int Number_RZ = 0;			//表示销售单号
    public static final int Number_YD	= 1;		//表示进货单号
    public static final int Number_JS	= 1;		//表示进货单号

    /**=======================================================================**
     *		[## public static boolean isNum (String in, int length, double min, double max) {} ]:
     *			参数   ：String对象表示被测字符串
     *					 length变量表示字符串最大长度，取值0，表示没有长度限定
     *					 min,max变量限定了String对象表示的数值范围，当(length > 0)时
     *			返回值 ：boolean 合法返回true
     *			修饰符 ：public static 可以不实例化对象而直接调用方法
     *			功能   ：测试字符串表示的数值及范围，且字符串只能是数字组成
     **=======================================================================**
     */
    public static boolean isNum (String in, int length, double min, double max) {
        String num = in;
        int point  = 0;						//'.'的个数
        int len = num.length ();
        if(length > 0) {
            if(len > length || len == 0) {	//判断字符串长度,不合法空返回false
                System.out.println ("simpletools.isNum(): Length error.");
                return false;
            }//Endif
        }//End if(length > 0)
        else
        if(len == 0) {					//判断字符串是否为空,空返回false
            System.out.println ("simpletools.isNum(): String is NULL");
            return false;
        }//End if(len == 0)
        for (int i = len - 1; i >=0; i--) {		//判断字符串只能是数字
            char ac = num.charAt (i);
            if(ac == '.' && point == 0 &&  i!= 0) {	//如果是'.'字符，且是第一次出现，且不是只有一个点
                if(i > len - 4) {			//判断小数位只能是两位
                    point++;
                    continue;
                }//Endif
            }//Endif
            if(ac < '0' || ac > '9' ) {
                System.out.println ("simpletools.isNum(): Character isn't ( '0' - '9' )");
                return false;
            }//Endif
        }//Endfor
        if(length !=0) {
            double s = Double.parseDouble (num);		//现在len为字符串表示的数值
            if(s < min  || s >max) {					//限制范围min-max之间
                System.out.println ("simpletools.isNum(): Amount limit error. ");
                return false;
            }//Endif
        }//End if(length != 0)
        return true;
    }

    /**=======================================================================**
     *		[## public static String getNumber (int type) {} ]:
     *			参数   ：int变量表示要获得什么类型的单号(见类头常量)
     *			返回值 ：String对象: 单号;
     *			修饰符 ：public static 可以不实例化对象而直接调用方法
     *			功能   ：自动分配单据编号, 自动递增
     **=======================================================================**
     */
    public static String getNumber (int type) {

        GregorianCalendar gc = new GregorianCalendar();
        String tp, number, month, day;		//单号标头, 单号记数位, 为月份, 为天
        int numLen = 0;						//单号的默认位数

        if(type == Number_RZ) {				//要获取销售单号
            tp = hotelin.getIniKey ("LodgName");
            number = hotelin.getIniKey ("LodgNumber");
        }else if(type == Number_YD){					//要获取进货单号
            tp = hotelin.getIniKey ("EngaName");
            number = hotelin.getIniKey ("EngaNumber");
        }else {
            tp = hotelin.getIniKey ("ChouName");
            number = hotelin.getIniKey ("ChouNumber");
        }

        numLen = number.length ();						//得到单号的默认位数
        number = Integer.parseInt (number) + 1 + "";	//将单号增1,再转成字符串

        //判断记数号是否超位(超出要求长度)
        if(number.equals ((int)Math.pow (10, numLen - 1) + "") && number.length() > 1)
            number = number.substring(1);				//单号记数位清零

        //for循环,用'0'为number补位 (i = 当前号码位数, i < numLen)
        for (int i = number.length (); i < numLen; i++) {
            number = "0" + number;
        }//Endfor

        //为月份补'0'
        month = gc.get (GregorianCalendar.MONTH) + 1 + "";
        if( month.length() == 1)
            month = "0" + month;

        //为天补'0'
        day = gc.get (GregorianCalendar.DAY_OF_MONTH) + "";
        if( day.length () == 1)
            day = "0" + day;

        //连接单号标头,日期,记数位;组成单据号码
        tp = tp + gc.get (GregorianCalendar.YEAR) + month + day + number;

        return tp;			//返回单号
    }

    /**=======================================================================**
     *		[## public static void savNumber (String num, int type) {} ]:
     *			参数   ：String对象表示要保存的单号
     *					 int变量表示要保存什么类型的单号(见类头常量)
     *			返回值 ：无
     *			修饰符 ：public static 可以不实例化对象而直接调用方法
     *			功能   ：将已用编号保存到INI文件
     **=======================================================================**
     */
    public static void savNumber (String num, int type) {
        //INI文件中的键名
        String ini[] = { "[SOFTINFO]", "UserName", "CompName", "[CONFIG]", "Soft_First",
                "Default_Link" , "Default_Page", "Sys_style", "[NUMBER]",
                "LodgName", "LodgNumber", "EngaName", "EngaNumber", "ChouName",
                "ChouNumber", "[HABITUS]", "Ck_Habitus", "Ck_Minute", "[PARTTIME]",
                "In_Room", "Out_Room1", "Out_Room2", "InsuDay", "ClockRoom1",
                "ClockRoom2", "InsuHour1", "InsuHour2", "[JDBC]", "DBFname",
                "UserID", "Password", "IP", "Access", "[ODBC]", "LinkName" };
        String bt;
        if(type == Number_RZ) {
            bt = hotelin.getIniKey ("LodgName");
            hotelin.setIniKey ("LodgNumber", num.substring (bt.length () + 8));
        }
        else if(type == Number_YD){
            bt = hotelin.getIniKey ("EngaName");
            hotelin.setIniKey ("EngaNumber", num.substring (bt.length () + 8));
        } else{
            bt = hotelin.getIniKey ("ChouName");
            hotelin.setIniKey ("ChouNumber", num.substring (bt.length () + 8));
        }
        //保存到INI文件
        hotelin.saveIni (ini);
    }

    /**=======================================================================**
     *		[## public static double getConsumeFactor(String sDate, String eDate) {} ]:
     *			参数   ：String sDate对象表示开始时间
     *					 String eDate变量表示结束时间
     *			返回值 ：double
     *			修饰符 ：public static 可以不实例化对象而直接调用方法
     *			功能   ：计算酒店计费天数  按INI文件中设置的
     **=======================================================================**
     */
    //获得消费系数
    public static double getConsumeFactor(String sDate, String eDate) {

        //获得开始日期时间的--年--月--日--时--分--秒
        String syh [] = sDate.trim ().split(" ");
        String symd[] = syh[0].trim().split("-");
        String shms[] = syh[1].trim().split(":");
        int sy = Integer.parseInt(symd[0]);
        int sM = Integer.parseInt(symd[1]);
        int sd = Integer.parseInt(symd[2]);
        int sh = Integer.parseInt(shms[0]);
        int sm = Integer.parseInt(shms[1]);
        int ss = Integer.parseInt(shms[2]);

        //获得结束日期时间的--年--月--日--时--分--秒
        String eyh [] = eDate.trim ().split(" ");
        String eymd[] = eyh[0].trim().split("-");
        String ehms[] = eyh[1].trim().split(":");
        int ey = Integer.parseInt(eymd[0]);
        int eM = Integer.parseInt(eymd[1]);
        int ed = Integer.parseInt(eymd[2]);
        int eh = Integer.parseInt(ehms[0]);
        int em = Integer.parseInt(ehms[1]);
        int es = Integer.parseInt(ehms[2]);

        //获得sDate的long值
        long sdt = new Timestamp(sy, sM, sd, sh, sm, ss, 0).getTime();
        //获得eDate的long值
        long edt = new Timestamp(ey, eM, ed, eh, em, es, 0).getTime();

        double db = 0;

        if(sdt > edt) {			//不合法	开始日期一定要小于结束日期
            db = -1;
            return db;
        }//Endif

        if(sdt == edt) {		//无时间差
            db = 0;
            return db;
        }//Endif


        int insuDay = (int)(edt - sdt)/3600000;
        if(insuDay < 24) {				//入住不到一天
            if(Integer.parseInt(hotelin.getIniKey("InsuDay")) == 1)
                db = 1;					//按全天计费
            else {
                if(insuDay > 1 && insuDay < 12)
                    db = 0.5;			//如果不按全天计费，则大于1小时按半天计费
                else
                    db = 1;				//如果不按全天计费，则大于12小时按全天计费
            }//Endif
            return db;
        }//Endif

        //几点之后按新的一天计费
        int inRoom = Integer.parseInt(hotelin.getIniKey("In_Room"));
        if(sh < inRoom) {
            db = 0.5;
        }//Endif
        sh = inRoom;		//多的时间已经加了系数，去掉多余的
        //几点之后按半天计费
        int outRoom1 = Integer.parseInt(hotelin.getIniKey("Out_Room1"));
        int outRoom2 = Integer.parseInt(hotelin.getIniKey("Out_Room2"));
        if(eh > outRoom1 && eh < outRoom2) {
            db = db + 0.5;
            eh = outRoom1;	//多的时间已经加了系数，去掉多余的
        }else if(eh >= outRoom2) {
            db = db + 1;
            eh = outRoom2;
        }//Endif

        //计算入住时间
        sdt = new Timestamp(sy, sM, sd, sh, sm, ss, 0).getTime();
        edt = new Timestamp(ey, eM, ed, eh, em, es, 0).getTime();

        db = db + (edt - sdt)/86400000;
        return db;
    }

    /**=======================================================================**
     *		[## public static double getClockFactor(String sDate, String eDate) {} ]:
     *			参数   ：String sDate对象表示开始时间
     *					 String eDate变量表示结束时间
     *			返回值 ：double
     *			修饰符 ：public static 可以不实例化对象而直接调用方法
     *			功能   ：计算酒店钟点房计费系数  按INI文件中设置的
     **=======================================================================**
     */
    public static double getClockFactor(String sDate, String eDate) {

        //获得开始日期时间的--年--月--日--时--分--秒
        String syh [] = sDate.trim ().split(" ");
        String symd[] = syh[0].trim().split("-");
        String shms[] = syh[1].trim().split(":");
        int sy = Integer.parseInt(symd[0]);
        int sM = Integer.parseInt(symd[1]);
        int sd = Integer.parseInt(symd[2]);
        int sh = Integer.parseInt(shms[0]);
        int sm = Integer.parseInt(shms[1]);
        int ss = Integer.parseInt(shms[2]);

        //获得结束日期时间的--年--月--日--时--分--秒
        String eyh [] = eDate.trim ().split(" ");
        String eymd[] = eyh[0].trim().split("-");
        String ehms[] = eyh[1].trim().split(":");
        int ey = Integer.parseInt(eymd[0]);
        int eM = Integer.parseInt(eymd[1]);
        int ed = Integer.parseInt(eymd[2]);
        int eh = Integer.parseInt(ehms[0]);
        int em = Integer.parseInt(ehms[1]);
        int es = Integer.parseInt(ehms[2]);

        //获得sDate的long值
        long sdt = new Timestamp(sy, sM, sd, sh, sm, ss, 0).getTime();
        //获得eDate的long值
        long edt = new Timestamp(ey, eM, ed, eh, em, es, 0).getTime();
        //获得时间差

        double db = 0;

        if(sdt > edt) {			//不合法	开始日期一定要小于结束日期
            db = -1;
            return db;
        }//Endif

        //获得两个时间之间有多少秒
        edt = (edt - sdt) / 1000;
        //--------------------------------------------------------------------//
        if(edt <= 60 * Integer.parseInt(hotelin.getIniKey("ClockRoom1"))) {
            db = 0;				//开房后几分钟开始计费，读INI文件设置
            return db;
        }//Endif
        //--------------------------------------------------------------------//
        if(edt / 60 < 60 * Integer.parseInt(hotelin.getIniKey("ClockRoom2"))) {
            db = 1;				//不足几小时按一个单位计费
            return db;
        }//Endif
        //--------------------------------------------------------------------//

        //除以上可能的正常计费公式如下
        db = edt / 3600;				//获得过去的小时数

        edt = edt % 3600 / 60;			//获得多余的分钟数

        if(edt > Integer.parseInt(hotelin.getIniKey("InsuHour2")) && edt <= Integer.parseInt(hotelin.getIniKey("InsuHour1"))) {
            db = db + 0.5;				//超过多少分，但小于多少分部分，收半价
        }else if(edt > Integer.parseInt(hotelin.getIniKey("InsuHour2"))) {
            db = db + 1;				//超过多少分的，收全价
        }//Endif
        //--------------------------------------------------------------------//

        return db;
    }
}
