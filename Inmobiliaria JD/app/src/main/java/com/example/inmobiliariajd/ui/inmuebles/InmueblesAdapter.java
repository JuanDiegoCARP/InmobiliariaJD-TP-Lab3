package com.example.inmobiliariajd.ui.inmuebles;

import static com.example.inmobiliariajd.request.ApiClient.URLBASE;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inmobiliariajd.R;
import com.example.inmobiliariajd.model.Inmueble;


import java.util.List;

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.InmuebleViewHolder> {

private List<Inmueble> lista;

private Context context;

public InmueblesAdapter(List<Inmueble> lista, Context context) {

    this.lista = lista;
    this.context = context;
}


@NonNull
@Override

public InmuebleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.inmueble_card, parent, false);
    return new InmuebleViewHolder(vista);
}

    @Override
    public void onBindViewHolder(@NonNull InmuebleViewHolder holder, int position) {
        Inmueble i = lista.get(position);
        holder.tvDireccion.setText(i.getDireccion());
        holder.tvPrecio.setText(String.valueOf(i.getValor()));
        holder.tvTipo.setText(i.getTipo());
        Glide.with(context)
                .load(URLBASE + i.getImagen())
                .placeholder(R.drawable.inm)
                .error("null")
                .into(holder.imgInmueble);
        holder.cardView.setOnClickListener(v ->{
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmueble", i);
            Navigation.findNavController((Activity)v.getContext(), R.id.nav_host_fragment_content_main).navigate(R.id.detalleInmueblesFragment, bundle);

        });

    }


    @Override
    public int getItemCount() {

    return lista.size();
    }

    public class InmuebleViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDireccion, tvPrecio, tvTipo;
        private ImageView imgInmueble;
        private CardView cardView;


        public InmuebleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            imgInmueble = itemView.findViewById(R.id.ivFotoInm);
            cardView = itemView.findViewById(R.id.idCard);
        }
    }
}
