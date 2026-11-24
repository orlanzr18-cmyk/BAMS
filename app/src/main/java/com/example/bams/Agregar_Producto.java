package com.example.bams;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// Solo muestra el diseño de activity_agregar_producto.xml
public class Agregar_Producto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_producto);

        findViewById(R.id.btnVolver).setOnClickListener(v -> finish());
    }
}