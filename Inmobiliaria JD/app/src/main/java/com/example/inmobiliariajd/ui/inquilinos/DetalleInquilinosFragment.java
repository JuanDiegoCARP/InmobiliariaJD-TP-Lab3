package com.example.inmobiliariajd.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliariajd.R;
import com.example.inmobiliariajd.databinding.FragmentDetalleInquilinosBinding;
import com.example.inmobiliariajd.model.Inquilino;

public class DetalleInquilinosFragment extends Fragment {

    private DetalleInquilinosViewModel mViewModel;
    private FragmentDetalleInquilinosBinding binding;


    public static DetalleInquilinosFragment newInstance() {
        return new DetalleInquilinosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DetalleInquilinosViewModel.class);
        binding = FragmentDetalleInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.getMinquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvCodigoInquilino.setText(inquilino.getIdInquilino());
                binding.tvNombre.setText(inquilino.getNombre());
                binding.tvApellido.setText(inquilino.getApellido());
                binding.tvDni.setText(inquilino.getDni());
                binding.tvTelefono.setText(inquilino.getTelefono());
                binding.tvEmail.setText(inquilino.getEmail());

            }
        });
        mViewModel.obtenerInquilino(getArguments());
        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}