package com.example.bams;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private TextInputEditText etCorreo;
    private TextInputEditText etPassword;
    private Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();

        etCorreo = findViewById(R.id.editTextText);
        etPassword = findViewById(R.id.editTextTextPassword);
        btnIniciarSesion = findViewById(R.id.btniniciarsesion);
        Button btnSalir = findViewById(R.id.btnsalir);

        btnIniciarSesion.setOnClickListener(v -> iniciarSesion());
        btnSalir.setOnClickListener(v -> finishAffinity());

        // Si ya hay una sesión abierta, entra directo
        if (auth.getCurrentUser() != null) {
            irAlMenu();
        }
    }

    private void iniciarSesion() {
        String correo = etCorreo.getText() == null ? "" : etCorreo.getText().toString().trim();
        String password = etPassword.getText() == null ? "" : etPassword.getText().toString();

        if (correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Ingresa tu correo y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        btnIniciarSesion.setEnabled(false);

        auth.signInWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, task -> {
                    btnIniciarSesion.setEnabled(true);
                    if (task.isSuccessful()) {
                        irAlMenu();
                    } else {
                        Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void irAlMenu() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}