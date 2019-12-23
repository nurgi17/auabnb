package n.b.m.auabnb.Netwrok;

import java.util.List;

import n.b.m.auabnb.Entities.Apartment;
import n.b.m.auabnb.Entities.City;
import n.b.m.auabnb.Entities.Photos;
import n.b.m.auabnb.Entities.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    //User requests
    @GET("/users/{id}")
    public Call<User> getUserWithID(@Path("id") long id);

    @GET("users")
    public Call<List<User>> getAllUsers();

    @POST("users")
    public Call<User> createUser(@Body User user);

    //User requests END

    //Apartments requests
    @GET("/apartments/{id}")
    public Call<Apartment> getApartmentWithID(@Path("id") long id);

    @GET("apartments")
    public Call<List<Apartment>> getAllApartments();

    @POST("apartments")
    public Call<Apartment> createApartment(@Body Apartment apartment);
    //Apartments requests END

    //Cities requests
    @GET("/cities/{id}")
    public Call<City> getCityWithID(@Path("id") long id);

    @GET("cities")
    public Call<List<City>> getAllCities();

    @POST("cities")
    public Call<City> createCity(@Body City city);
    //Cities requests END

    //Photos requests
    @GET("/photos/{id}")
    public Call<Photos> getPhotoWithID(@Path("id") long id);

    @GET("photos")
    public Call<List<Photos>> getAllPhotos();

    @POST("photos")
    public Call<Photos> createPhoto(@Body Photos photo);
    //Photos requests END
}
