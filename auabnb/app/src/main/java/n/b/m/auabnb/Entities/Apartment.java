package n.b.m.auabnb.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Apartment {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("city_id")
    @Expose
    private long city_id;

    @SerializedName("user_id")
    @Expose
    private long user_id;

    public Apartment(String title, double price, String description, String country, String address, long city_id, long user_id) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.country = country;
        this.address = address;
        this.city_id = city_id;
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
