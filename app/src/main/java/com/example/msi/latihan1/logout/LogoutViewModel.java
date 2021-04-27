package com.example.msi.latihan1.logout;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class LogoutViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LogoutViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("Belum bisa:)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}