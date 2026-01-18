package com.miapp.petagramwear;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import okhttp3.*;

import java.io.IOException;

public class FollowService extends IntentService {

    public FollowService() { super("FollowService"); }

    @Override
    protected void onHandleIntent(Intent intent){
        String idUsuario = intent.getStringExtra("id_usuario_instagram");
        EnviarFollow.enviar(idUsuario);
    }
}
