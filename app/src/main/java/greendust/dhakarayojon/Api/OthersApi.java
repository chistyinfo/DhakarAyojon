package greendust.dhakarayojon.Api;

import greendust.dhakarayojon.Model.ItemModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chisty on 8/25/2016.
 */
public interface OthersApi {
    @GET("ot.json")
    Call<ItemModel> getShout();

}
