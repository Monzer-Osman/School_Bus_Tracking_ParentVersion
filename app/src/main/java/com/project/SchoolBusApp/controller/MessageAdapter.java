package com.project.SchoolBusApp.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.SchoolBusApp.MessageDetails;
import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.model.Message;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<com.project.SchoolBusApp.controller.MessageAdapter.MessageViewHolder> {

    private final ArrayList<Message> messages;
    private final Context context;

    public MessageAdapter(Context context, ArrayList<Message> messages) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public com.project.SchoolBusApp.controller.MessageAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list, parent, false);
        return new com.project.SchoolBusApp.controller.MessageAdapter.MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.project.SchoolBusApp.controller.MessageAdapter.MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.title.setText(String.format("title: %s", message.getTitle()));
        holder.date.setText(String.format("Date: %s", message.getTime()));
        holder.message.setText(String.format("Message : %s", message.getMessage()));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView title;
        private final TextView date;
        private final TextView message;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title_view);
            date = itemView.findViewById(R.id.date);
            message = itemView.findViewById(R.id.message);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            Intent intent = new Intent(context, MessageDetails.class);
            intent.putExtra("title",messages.get(position).getTitle());
            intent.putExtra("date", messages.get(position).getTime().toString());
            intent.putExtra("message", messages.get(position).getMessage());

            context.startActivity(intent);
        }

    }
}

