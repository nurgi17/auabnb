package n.b.m.auabnb.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("photo")
    @Expose
    private byte[] photo;

    @SerializedName("apartment_id")
    @Expose
    private long apartment_id;

    public Photos(byte[] photo, long apartment_id) {
        this.id = id;
        this.photo = photo;
        this.apartment_id = apartment_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public long getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(long apartment_id) {
        this.apartment_id = apartment_id;
    }
}
