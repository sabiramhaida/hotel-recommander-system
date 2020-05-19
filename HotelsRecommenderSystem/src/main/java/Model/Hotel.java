package Model;

import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    ObjectId _id;

    private String name;
    private String Country;
    private String Region;
    private String Street;
    private String Zip;
    private String Location;

    @ElementCollection
    private List<String> Property_amenties = new ArrayList<String>();

    @ElementCollection
    private List<String> Room_features = new ArrayList<String>();

    @ElementCollection
        private List<String> Hotel_style = new ArrayList<String>();
    private Double Hotel_class;
    private Double price;
    private Double Hotel_score_reviews;
    private Double Location_score;
    private Double Cleanliness_score;
    private Double Service_score;
    private Double Value_score;

    public ObjectId getHotelId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public List<String> getProperty_amenties() {
        return Property_amenties;
    }

    public void setProperty_amenties(List<String> property_amenties) {
        Property_amenties = property_amenties;
    }

    public List<String> getRoom_features() {
        return Room_features;
    }

    public void setRoom_features(List<String> room_features) {
        Room_features = room_features;
    }

    public List<String> getHotel_style() {
        return Hotel_style;
    }

    public void setHotel_style(List<String> hotel_style) {
        Hotel_style = hotel_style;
    }

    public Double getHotel_class() {
        return Hotel_class;
    }

    public void setHotel_class(Double hotel_class) {
        Hotel_class = hotel_class;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getHotel_score_reviews() {
        return Hotel_score_reviews;
    }

    public void setHotel_score_reviews(Double hotel_score_reviews) {
        Hotel_score_reviews = hotel_score_reviews;
    }

    public Double getLocation_score() {
        return Location_score;
    }

    public void setLocation_score(Double location_score) {
        Location_score = location_score;
    }

    public Double getCleanliness_score() {
        return Cleanliness_score;
    }

    public void setCleanliness_score(Double cleanliness_score) {
        Cleanliness_score = cleanliness_score;
    }

    public Double getService_score() {
        return Service_score;
    }

    public void setService_score(Double service_score) {
        Service_score = service_score;
    }

    public Double getValue_score() {
        return Value_score;
    }

    public void setValue_score(Double value_score) {
        Value_score = value_score;
    }
}
