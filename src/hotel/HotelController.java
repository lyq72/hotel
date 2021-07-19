package hotel;

import Tools.SimpleTools;
import Tools.connect;
import Tools.hotelsql;
import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class HotelController  extends Application {

    @FXML
    private TableView<RoomTable> tableview,tableview1,tableview2,tableview3,tableview4,tableview5;

    @FXML
    private TableColumn<RoomTable, String> num,num1,num2,num3,num4,num5;

    @FXML
    private TableColumn<RoomTable, String> phone,phone1,phone2,phone3,phone4,phone5;

    @FXML
    private TableColumn<RoomTable, String> floor,floor1,floor2,floor3,floor4,floor5;

    @FXML
    private TableColumn<RoomTable, String> bed,bed1,bed2,bed3,bed4,bed5;

    @FXML
    private TableColumn<RoomTable, String> price,price1,price2,price3,price4,price5;

    @FXML
    private ImageView image;

    @FXML
    private TextArea text1;

    @FXML
    private TextArea text2;
    @FXML
    private Text time,time1,time2,time3,time4,time5,time6;

    @FXML
    private Text state,state1,state2,state3,state4,state5,state6;

    @FXML
    private Button btn,btn1,btn2,btn3,btn4,btn5,btn6;;

    @FXML
    private VBox vb;
    public String[][] toNew=new String[7][2];
    String toId;
    String toDate;
    String operation=EnHotelMain.operation;
    SimpleTools simpleTools=new SimpleTools();
    public  void initialize() {
        btn.setVisible(false);btn1.setVisible(false);btn2.setVisible(false);btn3.setVisible(false);btn4.setVisible(false);btn5.setVisible(false);btn6.setVisible(false);
        state.setText("");state1.setText("");state2.setText("");state3.setText("");state4.setText("");state5.setText("");state6.setText("");
        String sql = "SELECT roominfo.id,roominfo.phone,roominfo.floor,roomtype.bed,roomtype.price from roominfo,roomtype where roominfo.type_id =roomtype.type_id and roominfo.type_id='LX0001'AND state='可供';";
        setHotelTypeTableViewData(tableview, getHotelTypeTableViewData(sql), num, phone, floor, bed, price);
        String sql1 = "SELECT roominfo.id,roominfo.phone,roominfo.floor,roomtype.bed,roomtype.price from roominfo,roomtype where roominfo.type_id =roomtype.type_id and roominfo.type_id='LX0002'AND state='可供';";
        setHotelTypeTableViewData(tableview1, getHotelTypeTableViewData(sql1), num1, phone1, floor1, bed1, price1);
        String sql2 = "SELECT roominfo.id,roominfo.phone,roominfo.floor,roomtype.bed,roomtype.price from roominfo,roomtype where roominfo.type_id =roomtype.type_id and roominfo.type_id='LX0003'AND state='可供';";
        setHotelTypeTableViewData(tableview2, getHotelTypeTableViewData(sql2), num2, phone2, floor2, bed2, price2);
        String sql3 = "SELECT roominfo.id,roominfo.phone,roominfo.floor,roomtype.bed,roomtype.price from roominfo,roomtype where roominfo.type_id =roomtype.type_id and roominfo.type_id='LX0004'AND state='可供';";
        setHotelTypeTableViewData(tableview3, getHotelTypeTableViewData(sql3), num3, phone3, floor3, bed3, price3);
        String sql4 = "SELECT roominfo.id,roominfo.phone,roominfo.floor,roomtype.bed,roomtype.price from roominfo,roomtype where roominfo.type_id =roomtype.type_id and roominfo.type_id='LX0005'AND state='可供';";
        setHotelTypeTableViewData(tableview4, getHotelTypeTableViewData(sql4), num4, phone4, floor4, bed4, price4);
        String sql5 = "SELECT roominfo.id,roominfo.phone,roominfo.floor,roomtype.bed,roomtype.price from roominfo,roomtype where roominfo.type_id =roomtype.type_id and roominfo.type_id='LX0006'AND state='可供';";
        setHotelTypeTableViewData(tableview5, getHotelTypeTableViewData(sql5), num5, phone5, floor5, bed5, price5);
        tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showImage(newValue.getId());
            showWeed(newValue.getId());
        });
        tableview1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showImage(newValue.getId());
            showWeed(newValue.getId());
        });
        tableview2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showImage(newValue.getId());
            showWeed(newValue.getId());
        });
        tableview3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showImage(newValue.getId());
            showWeed(newValue.getId());
        });
        tableview4.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showImage(newValue.getId());
            showWeed(newValue.getId());
        });
        tableview5.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showImage(newValue.getId());
            showWeed(newValue.getId());
        });
    }

    private void showWeed(String id) {
        btn.setVisible(false);btn1.setVisible(false);btn2.setVisible(false);btn3.setVisible(false);btn4.setVisible(false);btn5.setVisible(false);btn6.setVisible(false);
        state.setText("");state1.setText("");state2.setText("");state3.setText("");state4.setText("");state5.setText("");state6.setText("");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date=df.format(new Date());
        ShowSituation(0,date,id,time,state,btn);
        Calendar calendar=new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,+1);
        String date1=df.format(calendar.getTime());
        ShowSituation(1,date1,id,time1,state1,btn1);
        calendar.add(calendar.DATE,+2);
        String date2=df.format(calendar.getTime());
        ShowSituation(2,date2,id,time2,state2,btn2);
        calendar.add(calendar.DATE,+3);
        String date3=df.format(calendar.getTime());
        ShowSituation(3,date3,id,time3,state3,btn3);
        calendar.add(calendar.DATE,+4);
        String date4=df.format(calendar.getTime());
        ShowSituation(4,date4,id,time4,state4,btn4);
        calendar.add(calendar.DATE,+5);
        String date5=df.format(calendar.getTime());
        ShowSituation(5,date5,id,time5,state5,btn5);
        calendar.add(calendar.DATE,+5);
        String date6=df.format(calendar.getTime());
        ShowSituation(6,date6,id,time6,state6,btn6);
    }

    private void ShowSituation(int i,String date, String id, Text time, Text state, Button btn) {
        toNew[i][0]=id;
        toNew[i][1]=date;
        time.setText(date);
        String sql="SELECT live_time FROM `order` WHERE room_id='"+id+"' AND state='预定成功'";
        ResultSet rs=hotelsql.executeQuery(sql);
        try {
            rs.next();
            if(rs.getRow()==0){
                btn.setVisible(true);
                state.setText("可约");
            }else {
                for (int j = 1; j <= rs.getRow(); j++) {
                    if (date.equals(rs.getString(j))) {
                        btn.setVisible(false);
                        state.setText("不可约");
                        break;
                    }else {
                        btn.setVisible(true);
                        state.setText("可约");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public  void showImage(String id) {
        String sql="SELECT type_id FROM roominfo WHERE id='"+id+"'; ";
        ResultSet rs=hotelsql.executeQuery(sql);
        try {
            rs.next();
            String type=rs.getString(1);
            Image im=new Image("pic/"+type+".png");
            image.setImage(im);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void setHotelTypeTableViewData(TableView tableView, ObservableList data, TableColumn<RoomTable, String> idColumn, TableColumn<RoomTable, String> phoneColumn,
                                                 TableColumn<RoomTable, String> floorColumn, TableColumn<RoomTable, String> bedColumn, TableColumn<RoomTable, String> priceColumn) {
        // 设置编号的数据
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        floorColumn.setCellValueFactory(cellData -> cellData.getValue().floorProperty());
        bedColumn.setCellValueFactory(cellData -> cellData.getValue().bedProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        tableView.setItems(data);
    }
    public static ObservableList<RoomTable> getHotelTypeTableViewData(String sql) {
        // 实例化User
        RoomInfo user = new RoomInfo();
        // 查询User类别表的所有数据
        List list = getRecordsDataBySql(sql);
        // 创建ObservableList<BookTypeBeanTableData>对象
        ObservableList<RoomTable> data = FXCollections.observableArrayList();
        // 循环遍历集合中的数据
        for (int i = 0; i < list.size(); i++) {
            RoomInfo r = (RoomInfo) list.get(i);
            // 将数据封装到BookTypeBeanTableData中
            RoomTable td = new RoomTable(r.getId(),r.getPhone(),r.getFloor(),r.getBed(),r.getPrice());
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
            while (rs.next()) {
                RoomInfo user=new RoomInfo();
                user.setId(rs.getString(1));
                user.setPhone(rs.getString(2));
                user.setFloor(rs.getString(3));
                user.setBed(rs.getString(4));
                user.setPrice(rs.getString(5));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @FXML
    void MaOrder(MouseEvent event) {
        if(operation.equals("OrdinaryUser")){
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "你无此权限！");
        }else {
            OrderMangement orderMangement=new OrderMangement();
            orderMangement.setVisible(true);
        }

    }

    @FXML
    void MaRoom(MouseEvent event) {
        if(operation.equals("OrdinaryUser")){
            simpleTools.informationDialog(Alert.AlertType.INFORMATION, "提示", "信息", "你无此权限！");
        }else {
            try {
                Manger manger = new Manger();
                manger.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void ShowOrder(MouseEvent event) {
        new EnMyOrder();
    }

    @FXML
    void refresh(MouseEvent event) {
        initialize();
    }
     void reserve(String toId, String toDate){
         new EnReserve(toId, toDate);
     }
    @FXML
    void btn(MouseEvent event) {
        toId=toNew[0][0];
        toDate=toNew[0][1];
        reserve(toId,toDate);
    }

    @FXML
    void btn1(MouseEvent event) {
        toId=toNew[1][0];
        toDate=toNew[1][1];
        reserve(toId,toDate);
    }

    @FXML
    void btn2(MouseEvent event) {
        toId=toNew[2][0];
        toDate=toNew[2][1];
        reserve(toId,toDate);
    }

    @FXML
    void btn3(MouseEvent event) {
        toId=toNew[3][0];
        toDate=toNew[3][1];
        reserve(toId,toDate);
    }

    @FXML
    void btn4(MouseEvent event) {
        toId=toNew[4][0];
        toDate=toNew[4][1];
        reserve(toId,toDate);
    }

    @FXML
    void btn5(MouseEvent event) {
        toId=toNew[5][0];
        toDate=toNew[5][1];
        reserve(toId,toDate);
    }

    @FXML
    void btn6(MouseEvent event) {
        toId=toNew[6][0];
        toDate=toNew[6][1];
        reserve(toId,toDate);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
