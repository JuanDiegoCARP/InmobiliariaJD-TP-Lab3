package com.example.inmobiliariajd.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.inmobiliariajd.databinding.FragmentPerfilBinding;
import com.example.inmobiliariajd.model.Propietario;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    private PerfilViewModel vm;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

    vm = new ViewModelProvider(this).get(PerfilViewModel.class);
    binding = FragmentPerfilBinding.inflate(inflater, container, false);

    vm.getMp().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
        @Override
        public void onChanged(Propietario p) {
            binding.etNombre.setText(p.getNombre());
            binding.etApellido.setText(p.getApellido());
            binding.etDni.setText(p.getDni());
            binding.etMail.setText(p.getMail());
            binding.etTelefono.setText(p.getTelefono());
        }
    });

    vm.getBmEstado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean aBoolean) {
            binding.etNombre.setEnabled(aBoolean);
            binding.etApellido.setEnabled(aBoolean);
            binding.etDni.setEnabled(aBoolean);
            binding.etMail.setEnabled(aBoolean);
            binding.etTelefono.setEnabled(aBoolean);
        }
    });
vm.getmIcono().observe(getViewLifecycleOwner(), new Observer<String>() {
    @Override
    public void onChanged(String string) {
    binding.btEditar.setText(string);
    }

});

vm.leerPropietario();
binding.btEditar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String nombre = binding.etNombre.getText().toString();
        String apellido = binding.etApellido.getText().toString();
        String dni = binding.etDni.getText().toString();
        String telefono = binding.etTelefono.getText().toString();
        String mail = binding.etMail.getText().toString();

        vm.guardar(binding.btEditar.getText().toString(), dni, nombre, apellido, mail, telefono);
    }
});

    return binding.getRoot();
    }


    }
