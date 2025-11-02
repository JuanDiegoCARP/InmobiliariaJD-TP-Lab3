package com.example.inmobiliariajd.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariajd.model.Inmueble;
import com.example.inmobiliariajd.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> inmueble = new MutableLiveData<>();
    public DetalleInmueblesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmueble(){
        return inmueble;
    }


    public void obtenerInmueble(Bundle inmuebleBundle){
        Inmueble inmueble = (Inmueble) inmuebleBundle.getSerializable("inmueble");

        if(inmueble != null){
           this.inmueble.setValue(inmueble);
       }

    }

    public void actualizarInmueble(Boolean disponible){
        Inmueble inmueble = new Inmueble();
        inmueble.setDisponible(disponible);
        inmueble.setIdInmueble(this.inmueble.getValue().getIdInmueble());
        String token = ApiClient.leerToken(getApplication());
        Call<Inmueble> llamada = ApiClient.getApiInmobiliario().actualizarInmueble("Bearer "+token, inmueble);
        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                   Toast.makeText(getApplication(), "Inmueble actualizado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplication(), "Error al actualizar el inmueble", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(getApplication(), "Error de red" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}