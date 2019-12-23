package n.b.m.auabnb.Netwrok;

public class Upload {
    private String mName;
    private String mImageUrl;
    private long apartment_id;
    private double price;
    private String title;

    public Upload(){ }

    public Upload(String mName, String mImageUrl, long apartment_id, double price, String title) {
        this.mName = mName;
        this.mImageUrl = mImageUrl;
        this.apartment_id = apartment_id;
        this.price = price;
        this.title = title;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
    public long getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(long apartment_id) {
        this.apartment_id = apartment_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
