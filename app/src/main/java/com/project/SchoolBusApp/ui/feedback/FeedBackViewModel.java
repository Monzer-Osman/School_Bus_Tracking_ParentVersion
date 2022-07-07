package com.project.SchoolBusApp.ui.feedback;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedBackViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FeedBackViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is FeedBack fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}