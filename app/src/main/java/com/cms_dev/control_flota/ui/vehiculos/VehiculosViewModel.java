package com.cms_dev.control_flota.ui.vehiculos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VehiculosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VehiculosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aqui estarán todos los vehiculos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}