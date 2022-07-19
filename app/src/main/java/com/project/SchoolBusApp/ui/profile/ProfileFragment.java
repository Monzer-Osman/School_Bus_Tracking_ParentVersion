package com.project.SchoolBusApp.ui.profile;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.controller.KidAdapter;
import com.project.SchoolBusApp.databinding.FragmentProfileBinding;
import com.project.SchoolBusApp.model.Kid;
import com.project.SchoolBusApp.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ArrayList<Kid> kids = new ArrayList<>();
    private RecyclerView recyclerView;
    private com.project.SchoolBusApp.controller.KidAdapter kidAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("IM_IN",0);
        StringBuilder fullName = new StringBuilder();

        TextView parentName = root.findViewById(R.id.textView3);
        TextView parentEmail = root.findViewById(R.id.textView4);
        TextView parentPhone = root.findViewById(R.id.textView7);

        fullName.append(sharedPreferences.getString("firstName","null"));
        fullName.append(" ");
        fullName.append(sharedPreferences.getString("lastName","null"));
        parentName.setText(fullName);
        parentEmail.setText(sharedPreferences.getString("email","null"));
        parentPhone.setText(sharedPreferences.getString("phone","null"));

        kidAdapter = new KidAdapter(root.getContext(), kids);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(kidAdapter);
        fillData();

        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    void fillData(){

        try {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("IM_IN",0);
            int userId = sharedPreferences.getInt("id",-1);
            ApiClient.getUserService().getKidsInfo(userId).enqueue(new Callback<ArrayList<Kid>>() {
                @Override
                public void onResponse(Call<ArrayList<Kid>> call, Response<ArrayList<Kid>> response) {

                    if (response.isSuccessful()) {
                        Log.d("log in : ", "success");

                        if(response.body() != null) {
                            Log.d("status : ", "i got the data ");
                            kids.addAll(response.body());
                            kidAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Kid>> call, Throwable t) {
                    Log.d("log in : ", "failed and didn't get the data");
                    Log.e("error is : ", t.toString());
                }
            });
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}