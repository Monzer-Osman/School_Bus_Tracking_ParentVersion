package com.project.SchoolBusApp.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.model.Kid;
import com.project.SchoolBusApp.network.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(@NonNull KidViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Kid Kid = kids.get(position);
        holder.name.setText(Kid.getName());
        holder.age.setText(String.format("Age: %s", Kid.getAge()));
        holder.level.setText(String.format("Level : %s", Kid.getLevel()));

        holder.attendance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {;
                    ApiClient.getUserService().updateStatus(kids.get(position).getId(), !b).enqueue(new Callback<Kid>() {
                        @Override
                        public void onResponse(Call<Kid> call, Response<Kid> response) {

                            if (response.isSuccessful()) {
                                Log.d("status : ", "success");
                            }
                        }

                        @Override
                        public void onFailure(Call<Kid> call, Throwable t) {
                            Log.d("status : ", "failed");
                        }

                    });
                }
                catch (Exception e) {
                    Log.e("error", e.toString());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return kids.size();
    }

    public class KidViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView age;
        private final TextView level;
        private final Switch attendance;
        //private final ImageView image;

        public KidViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_view99);
            age = itemView.findViewById(R.id.age);
            level = itemView.findViewById(R.id.level);
            attendance = itemView.findViewById(R.id.isAbscent);
            //image = itemView.findViewById(R.id.imageView);
        }
    }
}
