package com.project.SchoolBusApp.ui.feedback;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.project.SchoolBusApp.network.ApiClient;
import com.project.SchoolBusApp.databinding.FragmentFeedbackBinding;

import com.project.SchoolBusApp.model.FeedBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedBackFragment extends Fragment {

    private FragmentFeedbackBinding binding;
    private RatingBar rate;
    private TextView feedback;
    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFeedbackBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rate = binding.ratingBar2;
        feedback = binding.feedBackId2;
        button = binding.sendButton;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFeed();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void sendFeed(){

        float stars = rate.getRating();
        String feed = feedback.getText().toString();
        Log.d("stars : ", String.valueOf(stars));
        SharedPreferences sharedPreferences = binding.getRoot().getContext().getSharedPreferences("IM_IN",0);

        int user_id = sharedPreferences.getInt("id",0);

        Log.d("user id : ", String.valueOf(user_id));

        if(stars == 0 && feed.isEmpty()){
            Toast.makeText(binding.getRoot().getContext(), "Enter Your FeedBack ... ", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                FeedBack feedBack = new FeedBack(user_id, stars, feed);
                ApiClient.getUserService().sendFeedback(feedBack.getUser_id(),feedBack.getRate(),feedBack.getFeed()).enqueue(new Callback<FeedBack>() {
                    @Override
                    public void onResponse(Call<FeedBack> call, Response<FeedBack> response) {

                        if (response.isSuccessful()) {
                            Log.d("log in : ", "success");
                            feedBack.setFeed("success");
                        }
                    }

                    @Override
                    public void onFailure(Call<FeedBack> call, Throwable t) {
                        Log.d("log in : ", "failed");
                    }

                });
            }
            catch (Exception e) {
                Log.e("error", e.toString());
            }

            Toast.makeText(binding.getRoot().getContext(),"Thank You For Your Feed Back ...",Toast.LENGTH_LONG).show();
            rate.setRating(0);
            feedback.setText("");
        }
    }
}