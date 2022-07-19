package com.project.SchoolBusApp.ui.chat;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.SchoolBusApp.R;
import com.project.SchoolBusApp.databinding.FragmentSendMessageBinding;
import com.project.SchoolBusApp.model.Message;
import com.project.SchoolBusApp.network.ApiClient;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessage extends Fragment {

    private FragmentSendMessageBinding binding;
    private EditText title;
    private EditText message;
    private Button sendButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSendMessageBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        title = view.findViewById(R.id.title);
        message = view.findViewById(R.id.message);
        sendButton = view.findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        return view;
        //return inflater.inflate(R.layout.fragment_send_message, container, false);
    }

    private void sendMessage(){
        String t = title.getText().toString();
        String m = message.getText().toString();

        SharedPreferences sharedPreferences = binding.getRoot().getContext().getSharedPreferences("IM_IN",0);

        int user_id = sharedPreferences.getInt("id",0);

        Log.d("user id : ", String.valueOf(user_id));

        if(t.isEmpty() || m.isEmpty()){
            Toast.makeText(binding.getRoot().getContext(), "Fill the Empty Field ... ", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                Date d = new Date();
                ApiClient.getUserService().sendMessage(t, m, user_id, 0, d.toString()).enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {

                        if (response.isSuccessful()) {
                            Log.d("log in : ", "success");
                        }
                    }

                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Log.d("log in : ", "failed");
                    }

                });
            }
            catch (Exception e) {
                Log.e("error", e.toString());
            }

            Toast.makeText(binding.getRoot().getContext(),"Message Has Been Sent Successfully ...",Toast.LENGTH_LONG).show();
            title.setText("");
            message.setText("");
        }
    }
}