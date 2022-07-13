package com.project.SchoolBusApp.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.model.Kid;

import java.util.ArrayList;

public class KidAdapter extends RecyclerView.Adapter<KidAdapter.KidViewHolder>{


    private final ArrayList<Kid> kids;
    private final Context context;

    public KidAdapter(Context context, ArrayList<Kid>kids) {
        this.kids = kids;
        this.context = context;
    }

    @NonNull
    @Override
    public KidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kid_list, parent,false);
        return new KidViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KidViewHolder holder, int position) {
        Kid Kid = kids.get(position);
        holder.name.setText(Kid.getName());
        holder.age.setText(String.format("Age: %s", Kid.getAge()));
        holder.level.setText(String.format("Level : %s", Kid.getLevel()));
        //holder.image.setImageResource(Kid.getImage());
    }

    @Override
    public int getItemCount() {
        return kids.size();
    }

    public class KidViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView age;
        private final TextView level;
        //private final ImageView image;

        public KidViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_view99);
            age = itemView.findViewById(R.id.age);
            level = itemView.findViewById(R.id.level);
            //image = itemView.findViewById(R.id.imageView);
        }
    }
}
