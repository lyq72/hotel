package hotel;

public class ReserveInfo {
    private String id;
    private String username;
    private String user_type;
    private String operate_time;
    private String truename;
    private String phone;
    private String room_id;
    private String live_time;
    private String user_num;
    private String state;

    public ReserveInfo(){};
    public ReserveInfo(String id, String username, String user_type, String operate_time, String truename,
                       String phone, String room_id, String live_time, String user_num,String state){
        this.id=id;
        this.username=username;
        this.user_type=user_type;
        this.operate_time=operate_time;
        this.truename=truename;
        this.phone=phone;
        this.room_id=room_id;
        this.live_time=live_time;
        this.user_num=user_num;
        this.state=state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLive_time() {
        return live_time;
    }

    public void setLive_time(String live_time) {
        this.live_time = live_time;
    }

    public String getUser_num() {
        return user_num;
    }

    public void setUser_num(String user_num) {
        this.user_num = user_num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
