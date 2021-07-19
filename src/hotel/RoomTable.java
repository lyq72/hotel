package hotel;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class RoomTable {
    private final SimpleStringProperty id;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty floor;
    private final SimpleStringProperty bed;
    private final SimpleStringProperty price;

    public RoomTable(SimpleStringProperty id, SimpleStringProperty phone, SimpleStringProperty floor, SimpleStringProperty bed, SimpleStringProperty price) {
        this.id = id;
        this.phone = phone;
        this.floor = floor;
        this.bed = bed;
        this.price = price;
    }

    public RoomTable(String id, String phone, String floor, String bed, String price) {
        this.id = new SimpleStringProperty(id);
        this.phone = new SimpleStringProperty(phone);
        this.floor = new SimpleStringProperty(floor);
        this.bed = new SimpleStringProperty(bed);
        this.price = new SimpleStringProperty(price);
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

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getFloor() {
        return floor.get();
    }

    public SimpleStringProperty floorProperty() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor.set(floor);
    }

    public String getBed() {
        return bed.get();
    }

    public SimpleStringProperty bedProperty() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed.set(bed);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

}