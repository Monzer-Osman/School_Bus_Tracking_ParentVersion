package com.project.SchoolBusApp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.SchoolBusApp.controller.MessageAdapter;
import com.project.SchoolBusApp.controller.MessageAdapter;
import com.project.SchoolBusApp.databinding.FragmentOutboxBinding;
import com.project.SchoolBusApp.databinding.FragmentProfileBinding;
import com.project.SchoolBusApp.model.Message;
import com.project.SchoolBusApp.model.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Outbox extends Fragment {
    
    private FragmentOutboxBinding binding;
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private ArrayList<Message> messageList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOutboxBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        messageList = new ArrayList<>();

        messageAdapter = new MessageAdapter(root.getContext(), messageList);
        recyclerView = root.findViewById(R.id.recyclerView1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(messageAdapter);
        fillData();

        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    void fillData(){
        Date d = new Date();
        messageList.add(new Message(0,"Hello brother i was waiting for you.", "You got to really live your live with passion and some drive",d));
        messageList.add(new Message(1,"HI", "Ahmed",d));
        messageList.add(new Message(2,"Message", "This Message for testing issues",d));
        messageList.add(new Message(3,"Late", "I'm waiting for my kid ya hoo",d));
        messageList.add(new Message(4,"Where are you", "Ahmed says where are you brother",d));
        messageAdapter.notifyDataSetChanged();
    }
}