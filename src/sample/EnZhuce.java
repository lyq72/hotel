package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EnZhuce {
    public EnZhuce() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("zhuce.fxml"));
            AnchorPane root = null;
            root = loader.load();
            Stage primaryStage = new Stage();
            primaryStage.setTitle("注册窗口");
            primaryStage.setScene(new Scene(root, 550, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
