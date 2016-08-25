package greendust.dhakarayojon.Api;


import greendust.dhakarayojon.Model.ItemModel;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Joker on 7/24/16.
 */
public interface MyApi {
    @GET("da.json")
    Call<ItemModel> getShout();
}
