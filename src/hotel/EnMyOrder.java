package hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EnMyOrder {
    public EnMyOrder() {
        try {
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("MyOrder.fxml"));
            AnchorPane root= null;
            root = loader.load();
            Stage primaryStage=new Stage();
            primaryStage.setTitle("我的订单");
            primaryStage.setScene(new Scene(root, 850, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
