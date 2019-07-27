package com.example.paginglibrary;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhotoViewModel viewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
        viewModel.init();
        viewModel.getAllPhotos().observe(this, new Observer<List<PhotoModel>>() {
            @Override
            public void onChanged(@Nullable List<PhotoModel> photoModels) {
                generateDataList(photoModels);
                adapter.notifyDataSetChanged();
            }
        });



       /* Api api = RetrofitClient.getRetrofitInstance()
                .create(Api.class);

        Call<List<PhotoModel>> call = api.getAllPhotos();
        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {

            }
        });*/

    }

    private void generateDataList(List<PhotoModel> photoList) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
