package sample;

import Tools.hotelsql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class zhuce {
    String radomInt=(int)((Math.random()*9+1)*100000)+"";
    @FXML
    private TextField code;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField email1;

    @FXML
    private Button turnButton;

    @FXML
    private Button closeButton;
    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void fasong(ActionEvent event) {
        String tEmail=email1.getText();
        sendMailMessage(radomInt,tEmail);
    }
    public boolean sendMailMessage(String code,String tEmail) {
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("smtp.126.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            String emailaddress=tEmail;
            email.addTo(emailaddress);// 收件地址
            email.setFrom("woailyq0329@126.com", "lyq");//此处填邮箱地址和用户名,用户名可以任意填写

            email.setAuthentication("woailyq0329@126.com", "IIWPRZYFYVEVBQVH");//此处填写邮箱地址和客户端授权码

            email.setSubject("注册验证码");//此处填写邮件名，邮件名可任意填写
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);//此处填写邮件内容

            email.send();
            return true;

        } catch (EmailException e) {
            System.err.println("邮件发送失败");
            e.printStackTrace();
            return false;
        }
    }
    @FXML
    void turn(ActionEvent event) {
        String usern=username.getText();
        String passw=password.getText();
        String tEmail=email1.getText();
        if(code.getText().equals(radomInt)){
            try {
                String pk=code.getText();
                String sqlCode[] = new String[1];
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String time=df.format(new Date());
                sqlCode[0] = "insert into userinfo(username,pwd,email,opertator,time)values('" + usern + "','" + passw + "','" + tEmail + "','OrdinaryUser',"+"'"+ time+"'" + ")";
                hotelsql.runTransaction(sqlCode);
            }catch (Exception e){
                System.out.println("注册不成功");
            }
            JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            Stage stage = (Stage) turnButton.getScene().getWindow();
            stage.close();
        }else{
            JOptionPane.showMessageDialog(null, "验证码错误，注册失败", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
