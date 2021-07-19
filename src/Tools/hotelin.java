package Tools;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

public class hotelin {
    private static Properties ini = null;

    static {
        try	{
            ini = new Properties ();
            ini.load (new FileInputStream ("config.ini"));
        }catch (Exception ex) {
            System.out.println ("Load CONFIG.INI is false!!");
        }//End try
    }
    //防止实例化hotelin对象
    private hotelin() {
    }
    //获得INI文件中的指定键的键值
    public static String getIniKey (String k) {
        if(!ini.containsKey (k)) {		//是否有 k 这个键
            System.out.println ("The [ " + k + " ] Key is not exist!!");
            return "";
        }//End if(!ini.containsKey (k))
        return ini.get (k).toString ();
    }
    //设置k键的键值为v对象
    public static void setIniKey (String k, String v) {
        if(!ini.containsKey (k)) {		//是否有 k 这个键
            System.out.println ("The [ " + k + " ] Key is not exist!!");
            return;
        }//End if(!ini.containsKey (k))
        ini.put (k, v);
    }
    //将k字符数组中所有键所对应的键值保存到INI文件中
    public static void saveIni (String k[]) {
        try	{
            FileWriter fw = new FileWriter ("config.ini");
            BufferedWriter bw = new BufferedWriter ( fw );
            //循环变量i是k字符串数组的下标
            for (int i = 0; i < k.length; i++) {
                bw.write (k[i] + "=" + getIniKey (k[i]));
                bw.newLine ();
            }//End for
            bw.close ();
            fw.close ();
        }catch (Exception ex) {
            System.out.println ("Save CONFIG.INI is false.");
        }//End try
    }
}
