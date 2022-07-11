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
import com.project.SchoolBusApp.databinding.FragmentInboxBinding;
import com.project.SchoolBusApp.databinding.FragmentOutboxBinding;
import com.project.SchoolBusApp.model.Message;

import java.util.ArrayList;
import java.util.Date;

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
        Date d = new Date();
        messageList.add(new Message(0,"Hello brother for you.", "Live Your Life With Passion And Some Drive.",d));
        messageList.add(new Message(1,"HI how are you", "Ahmed",d));
        messageList.add(new Message(2,"we have meeting today", "This Message for testing issues",d));
        messageList.add(new Message(3,"come to my office", "I'm so angry, you haha",d));
        messageList.add(new Message(4,"good morning dode", "Monzer says he love what he do hahaha",d));
        messageAdapter.notifyDataSetChanged();
    }
}