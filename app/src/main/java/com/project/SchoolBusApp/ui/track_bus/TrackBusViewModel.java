package com.project.SchoolBusApp.ui.track_bus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrackBusViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrackBusViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Track-Bus fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}