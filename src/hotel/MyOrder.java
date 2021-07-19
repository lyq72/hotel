package hotel;

import Tools.SimpleTools;
import Tools.connect;
import Tools.hotelsql;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyOrder {

    @FXML
    private Text top;

    @FXML
    private TableView<MyOrderTable> tableview;

    @FXML
    private TableColumn<MyOrderTable, String> id;

    @FXML
    private TableColumn<MyOrderTable, String> name;

    @FXML
    private TableColumn<MyOrderTable, String> phone;

    @FXML
    private TableColumn<MyOrderTable, String> roomnum;

    @FXML
    private TableColumn<MyOrderTable, String> num;

    @FXML
    private TableColumn<MyOrderTable, String> subtime;

    @FXML
    private TableColumn<MyOrderTable, String> livetime;

    @FXML
    private TableColumn<MyOrderTable, String> state;

    @FXML
    private TextField tId;

    @FXML
    private TextField tPhone;

    @FXML
    private ComboBox<String> tLive;

    @FXML
    private Button change;

    @FXML
    private Button quxiao;
    String ItemPhone;
    String username=EnHotelMain.userid;
    ArrayList<String> list = new ArrayList<String>();
    SimpleTools simpleTools = new SimpleTools();
    public  void initialize() {
        top.setText(username + "的订单");
        try {
            String sql1 = "SELECT id,truename,phone,room_id,user_num,operate_time,live_time,state FROM `order` WHERE username='" + username + "';";
            setManageTableViewData(tableview, getManageTableViewData(sql1), id, name, phone,roomnum, num, subtime, livetime, state);

        } catch (Exception e) {
            System.out.println("管理员的表格显示有误");
        }
        tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tId.setText(newValue.getId());
            tPhone.setText(newValue.getPhone());
            ItemPhone=newValue.getPhone();
            ShowTime(newValue.getRoomnum());
            tLive.getItems().addAll(list);
        });
    }



    private void ShowTime(String id) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date=df.format(new Date());
        ShowSituation(date,id);
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,+1);
        String date1=df.format(calendar.getTime());
        ShowSituation(date1,id);
        calendar.add(calendar.DATE,+1);
        String date2=df.format(calendar.getTime());
        ShowSituation(date2,id);
        calendar.add(calendar.DATE,+1);
        String date3=df.format(calendar.getTime());
        ShowSituation(date3,id);
        calendar.add(calendar.DATE,+1);
        String date4=df.format(calendar.getTime());
        ShowSituation(date4,id);
        calendar.add(calendar.DATE,+1);
        String date5=df.format(calendar.getTime());
        ShowSituation(date5,id);
        calendar.add(calendar.DATE,+1);
        String date6=df.format(calendar.getTime());
        ShowSituation(date6,id);
    }

    private void ShowSituation(String date,String id) {
        String sql="SELECT live_time FROM `order` WHERE room_id='"+id+"'";
        ResultSet rs= hotelsql.executeQuery(sql);
        try {
            int count=0;
            rs.next();
            if (rs.getRow()==0){
                list.add(date);
            }else{
                for (int i=1;i<=rs.getRow();i++){
                    if (date.equals(rs.getString(i))){
                        count++;
                    }
                }
                if (count==0){
                    list.add(date);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //此为修改页面的酒店信息显示，与上面的方法相同
    public static void setManageTableViewData(TableView tableView, ObservableList data, TableColumn<MyOrderTable, String> idColumn, TableColumn<MyOrderTable, String> nameColumn,
                                              TableColumn<MyOrderTable, String> phoneColumn,TableColumn<MyOrderTable, String> roomnumColumn, TableColumn<MyOrderTable, String> numColumn, TableColumn<MyOrderTable, String> subtimeColumn
            , TableColumn<MyOrderTable, String> livetimeColumn, TableColumn<MyOrderTable, String> stateColumn) {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        roomnumColumn.setCellValueFactory(cellData -> cellData.getValue().roomnumProperty());
        numColumn.setCellValueFactory(cellData -> cellData.getValue().numProperty());
        subtimeColumn.setCellValueFactory(cellData -> cellData.getValue().subtimeProperty());
        livetimeColumn.setCellValueFactory(cellData -> cellData.getValue().livetimeProperty());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        // 将数据添加到表格控件中
        tableView.setItems(data);
    }

    public static ObservableList<MyOrderTable> getManageTableViewData(String sql) {
        ReserveInfo reserveInfo = new ReserveInfo();
        // 查询图书类别表的所有数据
        List list = getRecordsDataBySql(sql);
        // 创建ObservableList<BookTypeBeanTableData>对象
        ObservableList<MyOrderTable> data = FXCollections.observableArrayList();
        // 循环遍历集合中的数据
        for (int i = 0; i < list.size(); i++) {
            ReserveInfo r = (ReserveInfo) list.get(i);
            // 将数据封装到BookTypeBeanTableData中
            MyOrderTable td = new MyOrderTable(r.getId(),r.getTruename(),r.getPhone(),r.getRoom_id(),r.getUser_num(),r.getOperate_time(),r.getLive_time(),r.getState());
            // 将BookTypeBeanTableData对象添加到data中
            data.add(td);
        }
        // 返回数据
        return data;
    }
    public static List getRecordsDataBySql(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            //获得数据的连接
            conn = connect.getConnection();
            //获得Statement对象
            stmt = conn.createStatement();
            //发送SQL语句
            rs = stmt.executeQuery(sql);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ReserveInfo reserveInfo=new ReserveInfo();
                reserveInfo.setId(rs.getString(1));
                reserveInfo.setTruename(rs.getString(2));
                reserveInfo.setPhone(rs.getString(3));
                reserveInfo.setRoom_id(rs.getString(4));
                reserveInfo.setUser_num(rs.getString(5));
                reserveInfo.setOperate_time(rs.getString(6));
                reserveInfo.setLive_time(rs.getString(7));
                reserveInfo.setState(rs.getString(8));
                list.add(reserveInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @FXML
    void change(MouseEvent event) {
        String id = tId.getText();
        String newPhone = tPhone.getText();
        String newTime = tLive.getValue();
        boolean chang=false;
        String regex="^[1][3,4,5,7,8][0-9]{9}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(newPhone);
        boolean isMatch = m.matches();
        if (newPhone!=ItemPhone&&isMatch){
            String sql = "UPDATE `order` SET phone='" + newPhone + "' WHERE id='" + id + "'";
            int row = hotelsql.executeUpdate(sql);
            if (row>0) chang=true;
        }
        if (newTime != null) {
            String sql = "UPDATE `order` SET live_time='" + newTime + "' WHERE id='" + id + "'";
            int row = hotelsql.executeUpdate(sql);
            if (row>0) chang=true;
        }
        if (!chang) {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "修改失败,手机号格式不对");
        } else {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "修改成功");
            Stage stage = (Stage) change.getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    void quxiao(MouseEvent event) throws SQLException {
        String id = tId.getText();
        String sql1="SELECT live_time FROM `order` WHERE id='"+id+"'";
        ResultSet rs=hotelsql.executeQuery(sql1);
        rs.next();
        String livetime=rs.getString(1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date=df.format(new Date());
        if (date.equals(livetime)){
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "今日预约不得取消，定金不退回！");
        }
        String sql = "UPDATE `order` SET state='已取消' WHERE id='"+id+"'";
        int row = hotelsql.executeUpdate(sql);
        if (row>0){
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "取消成功，定金已原路返回");
        }else {
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "取消失败");
        }
    }

}
