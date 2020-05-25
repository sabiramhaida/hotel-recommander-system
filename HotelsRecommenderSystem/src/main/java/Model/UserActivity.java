package Model;

import org.bson.types.ObjectId;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
@Entity

public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ObjectId _id;

    private String hotel_name;
    private int date;
    private String username;
    private String activity;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }


    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
