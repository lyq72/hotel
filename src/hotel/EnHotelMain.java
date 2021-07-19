package hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EnHotelMain {
    //用户名，权限
    public static String userid, operation;
    //构造函数
    public EnHotelMain(String us, String pu) {
        userid = us;        //获得操作员名称
        operation = pu;        //获得操作员权限
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("HotelMain.fxml"));
            AnchorPane root= null;
            root = loader.load();
            Stage primaryStage=new Stage();
            primaryStage.setTitle("酒店");
            primaryStage.setScene(new Scene(root, 1000, 800));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
