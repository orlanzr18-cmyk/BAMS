package com.example.bams;

import android.widget.EditText;

public  class Validacion {
    public boolean vacio(EditText campo, String mensaje) {
        if (campo == null) {
            return false;
        }
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            campo.setError(mensaje);
            return false;
        }
        return true;
    }


    public boolean mayorACero(EditText campo, String mensajeVacio, String mensajeCero) {
        if (campo == null) {
            return false;
        }
        String texto = campo.getText().toString().trim().replace(",", ".");
        if (texto.isEmpty()) {
            campo.setError(mensajeVacio);
            return false;
        }
        try {
            double numero = Double.parseDouble(texto);
            if (numero <= 0) {
                campo.setError(mensajeCero);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            campo.setError("Ingrese un número válido");
            return false;
        }
    }

}
