package com.example.inmobiliariajd.ui.perfil;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariajd.model.Propietario;
import com.example.inmobiliariajd.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarContraseniaViewModel extends AndroidViewModel {
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mExito = new MutableLiveData<>();


    public CambiarContraseniaViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMerror(){
        return mError;
    }

    public LiveData<Boolean> getMexito(){
        return mExito;
    }

    public void cambiarContrasenia(String passActual, String passNueva){
        ApiClient.InmoServicio api = ApiClient.getApiInmobiliario();
        String token = ApiClient.leerToken(getApplication());
        Call<Propietario> llamda = api.actualizarContraseña("Bearer " + token, passActual, passNueva);
        if (passActual.isEmpty() || passNueva.isEmpty()){
            mError.postValue("Debe completar todos los campos");
            return;
        }
        if (passActual.equals(passNueva)){
            mError.postValue("Las contraseñas no pueden ser iguales");
            return;
        }
        llamda.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplication(), "Contraseña cambiada con exito", Toast.LENGTH_SHORT).show();
                    mExito.postValue(true);
                }else{
                    mError.postValue("Error al cambiar la contraseña");
                    mExito.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                mError.postValue("Error en el servidor ");
                mExito.postValue(false);
            }
        });

    }
}