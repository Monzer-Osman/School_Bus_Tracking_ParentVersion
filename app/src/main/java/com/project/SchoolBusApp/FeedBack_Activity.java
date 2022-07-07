package com.project.SchoolBusApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.model.FeedBack;

public class FeedBack_Activity extends AppCompatActivity {

    private RatingBar rate;
    private EditText feedback;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        rate = findViewById(R.id.ratingBar);
        feedback = findViewById(R.id.feed_back_id);
        submit = findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFeed();
            }
        });
    }

    public void sendFeed(){
        int stars = rate.getNumStars();
        String feed = feedback.getText().toString();
        if(stars == 0 && feed.isEmpty()){
            Toast.makeText(this,"Enter Your FeedBack ... ", Toast.LENGTH_SHORT).show();
        }
        else {
            FeedBack feedBack = new FeedBack(stars, feed);
            Toast.makeText(this,"Thank You For Your Feed Back ...",Toast.LENGTH_LONG).show();
            rate.setRating(0);
            feedback.setText("");
            //TODO send the feed back to the driver
        }
    }
}