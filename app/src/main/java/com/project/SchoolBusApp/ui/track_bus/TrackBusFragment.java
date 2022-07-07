package com.project.SchoolBusApp.ui.track_bus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.project.SchoolBusApp.databinding.FragmentTrackbusBinding;

public class TrackBusFragment extends Fragment {

    private FragmentTrackbusBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrackBusViewModel galleryViewModel =
                new ViewModelProvider(this).get(TrackBusViewModel.class);

        binding = FragmentTrackbusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}