package com.cms_dev.control_flota.ui.seguimiento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cms_dev.control_flota.databinding.FragmentSeguimientoBinding;

public class SeguimientoFragment extends Fragment {

    private FragmentSeguimientoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SeguimientoViewModel seguimientoViewModel =
                new ViewModelProvider(this).get(SeguimientoViewModel.class);

        binding = FragmentSeguimientoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSeguimiento;
        seguimientoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}