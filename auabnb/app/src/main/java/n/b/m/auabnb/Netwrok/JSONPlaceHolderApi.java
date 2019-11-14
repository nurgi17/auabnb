package n.b.m.auabnb.Netwrok;

import java.util.List;

import n.b.m.auabnb.Entities.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/users/{id}")
    public Call<User> getUserWithID(@Path("id") long id);

    @GET("users")
    public Call<List<User>> getAllUsers();

    @POST("users")
    public Call<User> createUser(@Body User user);
}
