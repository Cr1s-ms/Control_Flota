package com.cms_dev.control_flota.ui.seguimiento;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SeguimientoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SeguimientoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aqui se podra visualizar en tiempo real la ubicacion");
    }

    public LiveData<String> getText() {
        return mText;
    }
}