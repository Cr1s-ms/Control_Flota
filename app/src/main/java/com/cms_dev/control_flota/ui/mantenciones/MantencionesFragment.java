package com.cms_dev.control_flota.ui.mantenciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.cms_dev.control_flota.databinding.FragmentMantencionesBinding;

public class MantencionesFragment extends Fragment {

    private FragmentMantencionesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MantencionesViewModel mantencionesViewModel =
                new ViewModelProvider(this).get(MantencionesViewModel.class);

        binding = FragmentMantencionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMantenciones;
        mantencionesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}