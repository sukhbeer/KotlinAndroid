package com.example.paginglibrary;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

class PhotoViewModel extends ViewModel {

    private MutableLiveData<List<PhotoModel>> mutableLiveData;

    void init() {
        if (mutableLiveData != null) {
            return;
        }

        Repository repository = Repository.getInstance();
        mutableLiveData = repository.getAllPhotos();
    }

    LiveData<List<PhotoModel>> getAllPhotos() {
        return mutableLiveData;
    }


}
