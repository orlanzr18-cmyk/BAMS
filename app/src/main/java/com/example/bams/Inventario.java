package com.example.bams;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Inventario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventario);

        // Volver al menú principal
        findViewById(R.id.btnVolver2).setOnClickListener(v -> finish());

        // Agregar producto -> activity_agregar_producto
        findViewById(R.id.btnAgregarProducto).setOnClickListener(v ->
                startActivity(new Intent(Inventario.this, Agregar_Producto.class)));

        // Modificar producto -> activity_modificar_producto
        findViewById(R.id.btnModificarProducto).setOnClickListener(v ->
                startActivity(new Intent(Inventario.this, Modificar_Producto.class)));
    }
}