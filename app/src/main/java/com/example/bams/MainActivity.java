package com.example.bams;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnCerrarSesion).setOnClickListener(v ->
                mostrarDialogoCerrarSesion());

        // Botón Inventario/Producto -> abre la pantalla de inventario
        findViewById(R.id.btnProducto).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Inventario.class)));

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                mostrarDialogoCerrarSesion();
            }
        });
    }

    private void mostrarDialogoCerrarSesion() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que quieres cerrar sesión?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    public void abrirRegistrarVenta(View view) {
        startActivity(new Intent(MainActivity.this, Registrar_Ventas.class));
    }

    public void abrirVerVentas(View view) {
        startActivity(new Intent(MainActivity.this, Ver_Ventas.class));
    }
}