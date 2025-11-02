package com.example.inmobiliariajd.ui.inmuebles;



import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.inmobiliariajd.R;
import com.example.inmobiliariajd.databinding.FragmentDetalleInmueblesBinding;
import com.example.inmobiliariajd.databinding.FragmentInmueblesBinding;
import com.example.inmobiliariajd.request.ApiClient;


public class DetalleInmueblesFragment extends Fragment {

    private DetalleInmueblesViewModel mViewModel;
    private FragmentDetalleInmueblesBinding binding;

    public static DetalleInmueblesFragment newInstance() {
        return new DetalleInmueblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleInmueblesBinding.bind(getLayoutInflater().inflate(R.layout.fragment_detalle_inmuebles,container,false));
        mViewModel = new ViewModelProvider(this).get(DetalleInmueblesViewModel.class);

        mViewModel.getInmueble().observe(getViewLifecycleOwner(), inmueble -> {
            binding.tvIdInmueble.setText(inmueble.getIdInmueble() + "");
            binding.tvDireccionI.setText(inmueble.getDireccion());
            binding.tvUsoI.setText(inmueble.getUso());
            binding.tvAmbientesI.setText(inmueble.getAmbientes() + "");
            binding.tvLatitudI.setText(inmueble.getLatitud() + "");
            binding.tvLongitudI.setText(inmueble.getLongitud() + "");
            binding.tvValorI.setText(inmueble.getValor() + "");
            Glide.with(this)
                    .load(ApiClient.URLBASE + inmueble.getImagen())
                    .placeholder(R.drawable.inm)
                    .error("null")
                    .into(binding.imgInmueble);
            binding.checkDisponible.setChecked(inmueble.isDisponible());
        });
        mViewModel.obtenerInmueble(getArguments());

        binding.checkDisponible.setOnClickListener(v -> {
            mViewModel.actualizarInmueble(binding.checkDisponible.isChecked());
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}