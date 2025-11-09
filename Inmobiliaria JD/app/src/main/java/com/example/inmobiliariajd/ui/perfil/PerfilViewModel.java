package com.example.inmobiliariajd.ui.perfil;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariajd.model.Propietario;
import com.example.inmobiliariajd.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> mp = new MutableLiveData<>();

    private MutableLiveData<Boolean> bmEstado = new MutableLiveData<>();

    private MutableLiveData<String> mIcono = new MutableLiveData<>();

    private MutableLiveData<String> mError = new MutableLiveData<>();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getmError(){
        return mError;
    }

    public LiveData<Propietario> getMp() {
        return mp;
    }

    public LiveData<Boolean> getBmEstado() {
        return bmEstado;
    }

    public LiveData<String> getmIcono() {
        return mIcono;
    }



    public void guardar(String icono, String dni, String nombre, String apellido, String mail, String telefono){
        if(icono.equalsIgnoreCase("editar")){
            bmEstado.setValue(true);
            mIcono.setValue("guardar");
        }else{
            Propietario p = new Propietario();
            p.setIdPropietario(mp.getValue().getIdPropietario());
            p.setDni(dni);
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setMail(mail);
            p.setTelefono(telefono);
            p.setPassword(null);
            mIcono.setValue("editar");
            bmEstado.setValue(false);

            String token = ApiClient.leerToken(getApplication());
            Call<Propietario> llamada = ApiClient.getApiInmobiliario().actualizarPropietario("Bearer "+token,p);
            llamada.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getApplication(), "Propietario actualizado", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplication(), "Error al obtener el propietario", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {

                }
            });

        }}





    public void leerPropietario(){
        String token = ApiClient.leerToken(getApplication());
        Call<Propietario> llamada = ApiClient.getApiInmobiliario().obtenerPropietario("Bearer "+token);
        llamada.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    mp.postValue(response.body());
                }else{
                    Toast.makeText(getApplication(), "Error al obtener el propietario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
              Toast.makeText(getApplication(), "Error de red" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}