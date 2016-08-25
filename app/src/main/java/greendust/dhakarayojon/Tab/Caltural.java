package greendust.dhakarayojon.Tab;

import android.content.Intent;
import android.net.Uri;
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

import greendust.dhakarayojon.Adapter.CalturalAdapter;
import greendust.dhakarayojon.Api.MyApi;
import greendust.dhakarayojon.Model.ItemModel;
import greendust.dhakarayojon.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Caltural extends Fragment implements Callback<ItemModel> {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    Retrofit retrofit;

    String API = "http://www.padmafire.com/chistyinfo/dhakaayojon/";

    public Caltural() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_caltural, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh1);
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

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view1);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

            apiCall(retrofit);

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    apiCall(retrofit);
                }
            });


        // Inflate the layout for this fragment
//        AdView mAdView = (AdView) rootView.findViewById(R.id.adView1);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//
//        WebView webView2= (WebView) rootView.findViewById(R.id.webView2);
//        webView2.loadUrl("http://www.dsebd.org/mkt_depth_3.php");
//        webView2.getSettings().setJavaScriptEnabled(true);
//        webView2.setWebViewClient(new WebViewClient());

        return rootView;


    }


    public void dialNumber(String number){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +number));
        startActivity(intent);
    }



    private void apiCall(Retrofit retrofit) {
        MyApi myApi = retrofit.create(MyApi.class);

        Call<ItemModel> call = myApi.getShout();
        call.enqueue(this);
    }





    @Override
    public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
        ItemModel itemModel = response.body();
        CalturalAdapter adapter = new CalturalAdapter(itemModel);
        mRecyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onFailure(Call<ItemModel> call, Throwable t) {
    }



}
