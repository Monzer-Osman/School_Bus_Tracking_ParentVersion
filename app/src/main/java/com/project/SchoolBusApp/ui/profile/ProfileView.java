package com.project.SchoolBusApp.ui.profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.controller.KidAdapter;
import com.project.SchoolBusApp.model.Kid;

import java.util.ArrayList;
import java.util.List;

public class ProfileView extends AppCompatActivity {

    private List<Kid> KidsList;
    private RecyclerView recyclerView;
    private KidAdapter KidAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        recyclerView = findViewById(R.id.recyclerView);
        KidAdapter = new KidAdapter(this, KidsList);
        KidsList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(KidAdapter);
        fillData(KidsList);
    }

    void fillData(List<Kid> Kids){
        Kids.add(new Kid(0,12, 6,"Ahmed","Abdullah"));
        Kids.add(new Kid(1,14, 2,"Omar","Alfateh"));
        Kids.add(new Kid(2,11, 3,"Monzer","Osman"));
        Kids.add(new Kid(3,15, 6,"Mohammad","Qasem"));
        Kids.add(new Kid(4,13, 5,"Abdulkhalik","Alimi"));
        KidAdapter.notifyDataSetChanged();
    }

}
