package com.cms_dev.control_flota.ui.mantenciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MantencionesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MantencionesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mantenciones");
    }

    public LiveData<String> getText() {
        return mText;
    }
}