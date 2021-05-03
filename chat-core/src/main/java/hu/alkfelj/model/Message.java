package hu.alkfelj.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class Message {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(this,"id");
    private SimpleStringProperty content = new SimpleStringProperty(this, "content");
    private ObjectProperty<LocalDateTime> sendTime = new SimpleObjectProperty<>(this,"sendTime");
    private SimpleIntegerProperty room_id = new SimpleIntegerProperty(this,"room_id");
    private SimpleIntegerProperty userid = new SimpleIntegerProperty(this,"user_id");

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public LocalDateTime getSendTime() {
        return sendTime.get();
    }

    public ObjectProperty<LocalDateTime> sendTimeProperty() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime.set(sendTime);
    }

    public int getRoom_id() {
        return room_id.get();
    }

    public SimpleIntegerProperty room_idProperty() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id.set(room_id);
    }

    public int getUserid() {
        return userid.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid.set(userid);
    }
}
