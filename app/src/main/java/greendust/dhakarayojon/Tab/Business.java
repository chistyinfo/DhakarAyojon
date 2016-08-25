package greendust.dhakarayojon.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import greendust.dhakarayojon.Adapter.BusinessAdapter;
import greendust.dhakarayojon.Api.BusinessApi;
import greendust.dhakarayojon.Model.ItemModel;
import greendust.dhakarayojon.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Business extends Fragment implements Callback<ItemModel> {
    private RecyclerView mRecyclerView3;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    SwipeRefreshLayout swipeRefreshLayout3;
    Retrofit retrofit;

    String API = "http://www.padmafire.com/chistyinfo/dhakaayojon/";



    public Business() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_business, container, false);

        swipeRefreshLayout3 = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh3);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mRecyclerView3 = (RecyclerView) rootView.findViewById(R.id.recycler_view3);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView3.setLayoutManager(mLayoutManager);
        mRecyclerView3.setAdapter(mAdapter);

        apiCall(retrofit);

        swipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCall(retrofit);
            }
        });








        return rootView;
    }

    private void apiCall(Retrofit retrofit) {
        BusinessApi businessApi = retrofit.create(BusinessApi.class);

        Call<ItemModel> call = businessApi.getShout();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
        ItemModel itemModel = response.body();
        BusinessAdapter adapter = new BusinessAdapter(itemModel);
        mRecyclerView3.setAdapter(adapter);
        swipeRefreshLayout3.setRefreshing(false);

    }

    @Override
    public void onFailure(Call<ItemModel> call, Throwable t) {

    }
}
