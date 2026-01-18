package com.miapp.petagramwear;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class UsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        String idUsuario = getIntent().getStringExtra("id_usuario_instagram");
        TextView tv = findViewById(R.id.tvUsuario);
        tv.setText("Fotos recientes de: " + idUsuario);
    }
}
