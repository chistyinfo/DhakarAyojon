package greendust.retrofitwithjaso.Api;


import greendust.retrofitwithjaso.Model.ItemModel;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Joker on 7/24/16.
 */
public interface MyApi {
    @GET("feed.json")
    Call<ItemModel> getShout();
}
