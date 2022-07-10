package com.project.SchoolBusApp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.controller.KidAdapter;
import com.project.SchoolBusApp.databinding.FragmentProfileBinding;
import com.project.SchoolBusApp.model.Kid;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ArrayList<Kid> KidsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private com.project.SchoolBusApp.controller.KidAdapter KidAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //ProfileViewModel homeViewModel =
        //        new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        KidAdapter = new KidAdapter(root.getContext(), KidsList);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(KidAdapter);
        fillData();
        return root;
    }

    void fillData(){
        KidsList.add(new Kid(0,12, 6,"Ahmed","Abdullah"));
        KidsList.add(new Kid(1,14, 2,"Omar","Alfateh"));
        KidsList.add(new Kid(2,11, 3,"Monzer","Osman"));
        KidsList.add(new Kid(3,15, 6,"Mohammad","Qasem"));
        KidsList.add(new Kid(4,13, 5,"Abdulkhalik","Alimi"));
        KidAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}