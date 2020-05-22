package Model;

import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
@Entity

public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ObjectId _id;

    private String hotelName;
    private Date Date;
    private String userName;
    private String activity;
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }



}
