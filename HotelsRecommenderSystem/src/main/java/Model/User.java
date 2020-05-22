package Model;

import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            ObjectId _id;
    private String fullName;
    private String country;
    private String city;

    private String userName;
    private String password;

    @ElementCollection
    private List<String> Users_list = new ArrayList<String>();

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getUsers_list() {
        return Users_list;
    }

    public void setUsers_list(List<String> users_list) {
        Users_list = users_list;
    }
}
