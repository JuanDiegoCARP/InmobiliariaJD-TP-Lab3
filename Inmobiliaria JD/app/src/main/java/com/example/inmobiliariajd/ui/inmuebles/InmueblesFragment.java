package com.example.inmobiliariajd.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliariajd.R;
import com.example.inmobiliariajd.databinding.FragmentInmueblesBinding;
import com.example.inmobiliariajd.model.Inmueble;

import java.util.List;

public class InmueblesFragment extends Fragment {

    private InmueblesViewModel vm;
    private FragmentInmueblesBinding binding;




    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,
                              Bundle savedInstanceState) {
       vm = new ViewModelProvider(this).get(InmueblesViewModel.class);
       binding = FragmentInmueblesBinding.inflate(inflater, container, false);

       vm.getmInmueble().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
           @Override
           public void onChanged(List<Inmueble> inmuebles) {
            InmueblesAdapter adapter = new InmueblesAdapter(inmuebles, getContext());
            GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
            RecyclerView rv = binding.rvListaInmuebles;
            rv.setAdapter(adapter);
            rv.setLayoutManager(glm);
           }
       });

       binding.fabAgregarInmueble.setOnClickListener(v -> {
         NavHostFragment.findNavController(this)
                 .navigate(R.id.action_inmueblesFragment_to_cargarInmueblesFragment);
       });
        return binding.getRoot();
    }



}