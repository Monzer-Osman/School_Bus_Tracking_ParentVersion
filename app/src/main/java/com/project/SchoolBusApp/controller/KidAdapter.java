package com.project.SchoolBusApp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.model.Kid;

import java.util.List;

public class KidAdapter extends RecyclerView.Adapter<KidAdapter.KidViewHolder>{


    private final List<Kid> kids;
    private final Context context;

    public KidAdapter(Context context, List<Kid>kids) {
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
        holder.name.setText(Kid.getFirstName());
        holder.age.setText(String.format("Age: %s", Kid.getAge()));
        holder.level.setText(String.format("Level : %s", Kid.getLevel()));
        //holder.image.setImageResource(Kid.getImage());
    }

    @Override
    public int getItemCount() {
        return kids.size();
    }

    public class KidViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView name;
        private final TextView age;
        private final TextView level;
        //private final ImageView image;

        public KidViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name_view);
            age = itemView.findViewById(R.id.age);
            level = itemView.findViewById(R.id.level);
            //image = itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {

        }

//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(context, KidDetails.class);
//            int position = getAdapterPosition();
//            Kid Kid = kids.get(position);
//            intent.putExtra("name", Kid.getName());
//            intent.putExtra("genre", Kid.getGenre());
//            intent.putExtra("level", Kid.getlevel());
//            intent.putExtra("year", Kid.getYear());
//            intent.putExtra("image", Kid.getImage());
//            intent.putExtra("story", Kid.getStory());
//            context.startActivity(intent);
//        }
    }
}
