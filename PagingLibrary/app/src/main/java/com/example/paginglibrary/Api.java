package com.example.paginglibrary;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {
    @GET("/photos")
    Call<List<PhotoModel>> getAllPhotos();
}
