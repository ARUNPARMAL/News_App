package com.example.newsdaily;

import android.content.Context;
import android.widget.Toast;

import com.example.newsdaily.Models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;

    public RequestManager(Context context) {
        this.context = context;
    }

    Retrofit retrofit= new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsHeadlines(OnFetchDataListner listner, String category,String query){
        CallNewsApi callNewsApi= retrofit.create(CallNewsApi.class);

        Call<NewsApiResponse> call= callNewsApi.callheadlines("in",category,query,context.getString(R.string.api_key));

        try{
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                              if (!response.isSuccessful()){
                                  Toast.makeText(context, "Soem Error occured while fetching data", Toast.LENGTH_SHORT).show();
                              }

                              listner.onFetchData(response.body().getArticles(),response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                   listner.onError("RequestFailed"+t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
     }


        public interface CallNewsApi{
        @GET("top-headlines")
        Call<NewsApiResponse> callheadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_key
//                @Query("pageSize") int pageSize,
//                @Query("page\n") int page
//


        );
    }
}
