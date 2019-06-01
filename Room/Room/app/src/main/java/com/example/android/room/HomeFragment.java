package com.example.android.room;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        Button btnAdd = view.findViewById(R.id.add);
        btnAdd.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.add:
            MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new addUserFragment()).
                    addToBackStack(null).commit();
            break;
        }
    }
}
