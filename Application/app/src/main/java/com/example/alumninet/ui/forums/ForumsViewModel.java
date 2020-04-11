package com.example.alumninet.ui.forums;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForumsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ForumsViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is the 'Forums' fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}