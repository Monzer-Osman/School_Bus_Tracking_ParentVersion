package com.project.SchoolBusApp.ui.chat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.controller.MessageAdapter;
import com.project.SchoolBusApp.databinding.FragmentInboxBinding;
import com.project.SchoolBusApp.model.Message;
import com.project.SchoolBusApp.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inbox extends Fragment {

    private FragmentInboxBinding binding;
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private ArrayList<Message> messageList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInboxBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        messageList = new ArrayList<>();

        messageAdapter = new MessageAdapter(root.getContext(), messageList);
        recyclerView = root.findViewById(R.id.recyclerView2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(messageAdapter);
        fillData();

        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    void fillData(){

        try {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("IM_IN",0);
            int userId = sharedPreferences.getInt("id",-1);
            ApiClient.getUserService().inbox(userId).enqueue(new Callback<ArrayList<Message>>() {
                @Override
                public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {

                    if (response.isSuccessful()) {
                        Log.d("log in : ", "success");
                        Log.d("status : ", "i got the data ");

                        if(response.body() != null) {
                            Log.d("status : ", "i got the data ");
                            messageList.addAll(response.body());
                            ArrayList<Message> tmp = new ArrayList<>(messageList);
                            for(int j = 0; j < messageList.size(); j++) messageList.set(j,tmp.get(tmp.size()-j-1));
                            messageAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                    Log.d("log in : ", "failed and didn't get the data");
                    Log.e("error is : ", t.toString());
                }
            });
        }
        catch (Exception e) {
            Log.e("error", e.toString());
        }
//        Date d = new Date();
//
//        messageList.add(new Message(0,"Hello brother for you.", "Live Your Life With Passion And Some Drive.",d));
//        messageList.add(new Message(1,"HI how are you", "Ahmed",d));
//        messageList.add(new Message(2,"we have meeting today", "This Message for testing issues",d));
//        messageList.add(new Message(3,"come to my office", "I'm so angry, you haha",d));
//        messageList.add(new Message(4,"good morning dode", "Monzer says he love what he do hahaha",d));
//        messageAdapter.notifyDataSetChanged();
    }
}