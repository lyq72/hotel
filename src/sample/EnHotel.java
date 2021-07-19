package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EnHotel extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("登录界面");
        primaryStage.setScene(new Scene(root, 550, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }
}
