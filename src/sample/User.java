package sample;

import Tools.SimpleTools;
import Tools.hotelsql;
import hotel.EnHotelMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    @FXML
    private Stage stage;

        @FXML
        private TextField username;

        @FXML
        private Button dengLu;

        @FXML
        private TextField password;

        @FXML
        private Button closeButton;
        public String user;
        public String pass;
        public String opertator;
        @FXML
        void close(ActionEvent event) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }

        @FXML
       public String Login(ActionEvent event) throws SQLException {
            user=username.getText();
            pass=password.getText();
            System.out.println(user);
            String code="select pwd,opertator from userinfo where username='"+user+"'";
            ResultSet rs=hotelsql.executeQuery(code) ;
            SimpleTools simpleTools = new SimpleTools();
            try{
                if(user==null||pass==null)simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "用户名或密码不能为空！");else {
                    if (rs.next()) {//用户名存在
                        if (pass.equals(rs.getString(1))) {
                            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "登录成功！");
                            opertator = rs.getString(2);		//用户类型
                            new EnHotelMain(user, opertator);        //进入主程序窗口(用户名, 权限)
                        } else {
                            simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "密码不正确！");
                        }
                    } else {
                        simpleTools.informationDialog(Alert.AlertType.WARNING, "提示", "警告", "用户名不存在！");
                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return user;
        };

        @FXML
        void create(ActionEvent event) {
            new EnZhuce();
        }

}
