package com.miapp.petagramwear;

import android.util.Log;
import okhttp3.*;

import java.io.IOException;

public class EnviarFollow {

    public static void enviar(String idUsuarioInstagram){
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = "{ \"id_usuario_instagram\":\""+idUsuarioInstagram+"\" }";

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url("https://tuapp.herokuapp.com/follow-unfollow")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e){
                Log.e("EnviarFollow", "Error: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException{
                if(response.isSuccessful()){
                    Log.d("EnviarFollow", "Follow/Unfollow ejecutado correctamente");
                } else {
                    Log.e("EnviarFollow", "Error en Follow/Unfollow");
                }
            }
        });
    }
}
