package com.cms_dev.control_flota.ui.conductores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConductoresViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ConductoresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Conductores");
    }

    public LiveData<String> getText() {
        return mText;
    }
}