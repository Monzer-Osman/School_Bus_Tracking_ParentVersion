package com.project.SchoolBusApp.ui.feedback;

import android.os.Bundle;
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

import com.project.SchoolBusApp.databinding.FragmentFeedbackBinding;

import com.project.SchoolBusApp.model.FeedBack;

public class FeedBackFragment extends Fragment {

    private FragmentFeedbackBinding binding;
    private RatingBar rate;
    private TextView feedback;
    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FeedBackViewModel slideshowViewModel =
                new ViewModelProvider(this).get(FeedBackViewModel.class);

        binding = FragmentFeedbackBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rate = binding.ratingBar2;
        feedback = binding.feedBackId2;
        button = binding.button2;

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
        int stars = rate.getNumStars();
        String feed = feedback.getText().toString();
        if(stars == 0 && feed.isEmpty()){
            Toast.makeText(binding.getRoot().getContext(), "Enter Your FeedBack ... ", Toast.LENGTH_SHORT).show();
        }
        else {
            FeedBack feedBack = new FeedBack(stars, feed);
            Toast.makeText(binding.getRoot().getContext(),"Thank You For Your Feed Back ...",Toast.LENGTH_LONG).show();
            rate.setRating(0);
            feedback.setText("");
            //TODO send the feed back to the driver
        }
    }
}