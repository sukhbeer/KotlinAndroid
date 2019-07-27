package com.example.paginglibrary;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository randomData_repository;

    public static Repository getInstance() {
        if (randomData_repository == null) {
            randomData_repository = new Repository();
        }
        return randomData_repository;
    }

    private Api api;

    private Repository() {
        api = RetrofitClient.getRetrofitInstance().create(Api.class);
    }


    MutableLiveData<List<PhotoModel>> getAllPhotos() {
        final MutableLiveData<List<PhotoModel>> data = new
                MutableLiveData<>();

        api.getAllPhotos().enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {

            }
        });

        /*api.getAllPhotos().enqueue(new Callback<PhotoModel>() {
            @Override
            public void onResponse(@NonNull Call<PhotoModel> call, @NonNull Response<PhotoModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<PhotoModel> call, @NonNull Throwable t) {

            }
        });


        /*api.getAllPhotos().enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call,@NonNull Response<List<PhotoModel>> response) {
                if(response.isSuccessful()){
                   data = response ;
                }
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                data.setValue(null);
            }
        });*/
        return data;
    }
}
