package com.example.inmobiliariajd.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliariajd.databinding.FragmentInquilinosBinding;
import com.example.inmobiliariajd.model.Inmueble;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private InquilinosViewModel mViewModel;
    private FragmentInquilinosBinding binding;


    public static InquilinosFragment newInstance() {
        return new InquilinosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InquilinosViewModel.class);
        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.mInquilinos.observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                InquilinosAdapter inquilinoAdapter = new InquilinosAdapter(inmuebles, getContext());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                RecyclerView recyclerView = binding.listaInquilinos;
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(inquilinoAdapter);

            }
        });
        mViewModel.obtenerInquilinos();




        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}