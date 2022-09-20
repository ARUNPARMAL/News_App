package com.example.newsdaily;

import com.example.newsdaily.Models.Articles;

import java.util.List;

public interface OnFetchDataListner<NewsApiResponse> {
    void onFetchData(List<Articles> list,String message);

    void onError(String message);

}
