package com.example.alumninet.ui.connections;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConnectionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConnectionsViewModel() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}