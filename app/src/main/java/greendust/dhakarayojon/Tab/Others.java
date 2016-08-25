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

import greendust.dhakarayojon.Adapter.OthersAdapter;
import greendust.dhakarayojon.Api.OthersApi;
import greendust.dhakarayojon.Model.ItemModel;
import greendust.dhakarayojon.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Others extends Fragment implements Callback<ItemModel> {
    private RecyclerView mRecyclerView4;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    SwipeRefreshLayout swipeRefreshLayout4;
    Retrofit retrofit;

    String API = "http://www.padmafire.com/chistyinfo/dhakaayojon/";


    public Others() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_others, container, false);

        swipeRefreshLayout4 = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh4);
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

        mRecyclerView4 = (RecyclerView) rootView.findViewById(R.id.recycler_view4);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView4.setLayoutManager(mLayoutManager);
        mRecyclerView4.setAdapter(mAdapter);

        apiCall(retrofit);

        swipeRefreshLayout4.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCall(retrofit);
            }
        });




       



        return rootView;

    }

    private void apiCall(Retrofit retrofit) {
        OthersApi othersApi = retrofit.create(OthersApi.class);

        Call<ItemModel> call = othersApi.getShout();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
        ItemModel itemModel = response.body();
        OthersAdapter adapter = new OthersAdapter(itemModel);
        mRecyclerView4.setAdapter(adapter);
        swipeRefreshLayout4.setRefreshing(false);


    }

    @Override
    public void onFailure(Call<ItemModel> call, Throwable t) {

    }
}
