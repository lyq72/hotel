package hotel;

public class RoomInfo {
    private String id;
    private String type_id;
    private String phone;
    private String floor;
    private String state;
    private String type;
    private String bed;
    private String price;
    public RoomInfo(){}
    public RoomInfo(String id, String type_id, String phone, String floor , String state, String type, String bed, String price){
        this.id=id;
        this.type_id=type_id;
        this.phone=phone;
        this.floor=floor;
        this.state=state;
        this.type=type;
        this.bed=bed;
        this.price=price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
