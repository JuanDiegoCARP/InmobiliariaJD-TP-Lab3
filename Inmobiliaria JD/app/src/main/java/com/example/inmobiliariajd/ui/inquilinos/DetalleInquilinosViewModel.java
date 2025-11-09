package com.example.inmobiliariajd.ui.inquilinos;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariajd.model.Contratos;
import com.example.inmobiliariajd.model.Inquilino;
import com.example.inmobiliariajd.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInquilinosViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> mInquilino = new MutableLiveData<>();


    public DetalleInquilinosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inquilino> getMinquilino() {
        return mInquilino;
    }


    public void obtenerInquilino(Bundle idInmuebleBundle) {
        int idInmueble = idInmuebleBundle.getInt("id_inmueble");
        ApiClient.InmoServicio api = ApiClient.getApiInmobiliario();
        String token = ApiClient.leerToken(getApplication());
        Call<Contratos> llamada = api.obtenerContratosPorInmuebles("Bearer " + token, idInmueble);
        llamada.enqueue(new Callback<Contratos>() {
            @Override
            public void onResponse(Call<Contratos> call, Response<Contratos> response) {
                if (response.isSuccessful()) {
                    mInquilino.postValue(response.body().getInquilino());
                } else {
                    Toast.makeText(getApplication(), "Error al obtener inquilino", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contratos> call, Throwable t) {
                Log.e("API_ERROR", "Error al obtener inquilino: " + t.getMessage(), t);
                mInquilino.setValue(null);
            }
        });
    }
}