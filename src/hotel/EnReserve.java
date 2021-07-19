package hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EnReserve {
    public static String roomid, date;

    //构造函数
    public EnReserve(String toid, String todate) {
        roomid = toid;        //获得操作员名称
        date = todate;        //获得操作员权限
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("Reserve.fxml"));
            AnchorPane root= null;
            root = loader.load();
            Stage primaryStage=new Stage();
            primaryStage.setTitle("提交订单");
            primaryStage.setScene(new Scene(root, 400, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EnReserve() {

    }
}
