package hotel;

import javafx.beans.property.SimpleStringProperty;

public class MyOrderTable {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty phone;
    private SimpleStringProperty roomnum;
    private SimpleStringProperty num;
    private SimpleStringProperty subtime;
    private SimpleStringProperty livetime;
    private SimpleStringProperty state;
    public MyOrderTable(){}
    public MyOrderTable(String id, String name, String phone,String roomnum, String num, String subtime, String livetime, String state){
        this.id=new SimpleStringProperty(id);
        this.name=new SimpleStringProperty(name);
        this.phone=new SimpleStringProperty(phone);
        this.roomnum=new SimpleStringProperty(roomnum);
        this.num=new SimpleStringProperty(num);
        this.subtime=new SimpleStringProperty(subtime);
        this.livetime=new SimpleStringProperty(livetime);
        this.state=new SimpleStringProperty(state);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getRoomnum() {
        return roomnum.get();
    }

    public SimpleStringProperty roomnumProperty() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum.set(roomnum);
    }

    public String getNum() {
        return num.get();
    }

    public SimpleStringProperty numProperty() {
        return num;
    }

    public void setNum(String num) {
        this.num.set(num);
    }

    public String getSubtime() {
        return subtime.get();
    }

    public SimpleStringProperty subtimeProperty() {
        return subtime;
    }

    public void setSubtime(String subtime) {
        this.subtime.set(subtime);
    }

    public String getLivetime() {
        return livetime.get();
    }

    public SimpleStringProperty livetimeProperty() {
        return livetime;
    }

    public void setLivetime(String livetime) {
        this.livetime.set(livetime);
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }
}
