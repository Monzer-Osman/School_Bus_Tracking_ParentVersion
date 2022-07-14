package com.project.SchoolBusApp.ui.logout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.project.SchoolBusApp.databinding.FragmentLogoutBinding;
import com.project.SchoolBusApp.ui.login.ui_login.LoginActivity;

public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;

    @SuppressLint("ApplySharedPref")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("IM_IN",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(root.getContext(),LoginActivity.class);
        getContext().startActivity(intent);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}