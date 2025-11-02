package com.example.inmobiliariajd.ui.inmuebles;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariajd.model.Inmueble;
import com.example.inmobiliariajd.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InmueblesViewModel extends AndroidViewModel {

private final MutableLiveData<String> mText = new MutableLiveData<>();
private final MutableLiveData<List<Inmueble>> mInmueble = new MutableLiveData<>();

    public InmueblesViewModel(@NonNull Application application) {

        super(application);
        leerInmuebles();
    }

public LiveData<String> getmText() {
    return mText;
}
public LiveData<List<Inmueble>> getmInmueble() {
    return mInmueble;
}

public void leerInmuebles() {
    String token = ApiClient.leerToken(getApplication());
    ApiClient.InmoServicio api = ApiClient.getApiInmobiliario();
    Call<List<Inmueble>> llamada = api.obtenerInmuebles("Bearer "+token);
    llamada.enqueue(new Callback<List<Inmueble>>() {
        @Override
        public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
            if(response.isSuccessful()){
            mInmueble.postValue(response.body());

            }else {
            Toast.makeText(getApplication(), "No hay inmuebles disponibles"+ response.message(), Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onFailure(Call<List<Inmueble>> call, Throwable t) {
            Toast.makeText(getApplication(), "Error de servidor" + t.getMessage(), Toast.LENGTH_LONG).show();
        }
    });

}

}