package com.example.bams;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class Agregar_Producto_Venta extends AppCompatActivity {

    EditText txtBuscar, txtCantidad, txtprecio, txtTotal;
    TextView tvNombreProducto, tvMarcaProducto, tvStockProducto;
    Button btnBuscar, btnAgregar, btnCancelar, btnVolver;

    ProductoItem seleccionado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto_venta);

        txtBuscar = findViewById(R.id.txtBuscar);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtprecio = findViewById(R.id.txtprecio);
        txtTotal = findViewById(R.id.txtTotal);
        tvNombreProducto = findViewById(R.id.tvNombreProducto);
        tvStockProducto = findViewById(R.id.tvStockProducto);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnVolver = findViewById(R.id.btnVolver);

        // Por ahora no se maneja stock
        tvStockProducto.setVisibility(View.GONE);

        // El lector de código de barras escribe en este campo
        txtBuscar.requestFocus();

        btnBuscar.setOnClickListener(v -> buscarProducto());

        // Enter del teclado o del lector de código de barras
        txtBuscar.setOnEditorActionListener((v, actionId, event) -> {
            boolean esEnter = event != null
                    && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN;
            if (actionId == EditorInfo.IME_ACTION_SEARCH || esEnter) {
                buscarProducto();
                return true;
            }
            return false;
        });

        // Total en vivo al escribir la cantidad
        txtCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularTotal();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnAgregar.setOnClickListener(v -> {
            if (validar()) {
                agregarProducto();
            }
        });

        btnCancelar.setOnClickListener(v -> cancelar());
        btnVolver.setOnClickListener(v -> cancelar());
    }

    // ---------- BUSCAR ----------
    private void buscarProducto() {
        String texto = txtBuscar.getText().toString().trim();

        if (texto.isEmpty()) {
            txtBuscar.setError("EL CAMPO BUSCAR NO PUEDE ESTAR VACIO");
            return;
        }

        // 1. Se busca por código de barras
        BamsDatos.buscarProductoPorCodigo(texto, new Resultado<List<ProductoItem>>() {
            @Override
            public void onExito(List<ProductoItem> dato) {
                if (!dato.isEmpty()) {
                    mostrarProducto(dato.get(0));
                } else {
                    buscarPorNombre(texto);
                }
            }

            @Override
            public void onError(String mensaje) {
                Toast.makeText(Agregar_Producto_Venta.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    // 2. Si no hay código igual, se busca por nombre
    private void buscarPorNombre(String texto) {
        BamsDatos.listarProductos(new Resultado<List<ProductoItem>>() {
            @Override
            public void onExito(List<ProductoItem> dato) {
                for (ProductoItem p : dato) {
                    if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                        mostrarProducto(p);
                        return;
                    }
                }
                limpiarSeleccion();
                Toast.makeText(Agregar_Producto_Venta.this,
                        "No se encontró ningún producto", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String mensaje) {
                Toast.makeText(Agregar_Producto_Venta.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrarProducto(ProductoItem p) {
        seleccionado = p;
        tvNombreProducto.setText(p.getNombre());
        txtprecio.setText(String.format(Locale.US, "L %.2f", p.getPrecio()));
        txtCantidad.setText("");
        txtTotal.setText("");
        txtCantidad.requestFocus();
    }

    private void limpiarSeleccion() {
        seleccionado = null;
        tvNombreProducto.setText("Producto no seleccionado");
        txtprecio.setText("");
        txtCantidad.setText("");
        txtTotal.setText("");
    }

    // ---------- TOTAL ----------
    private void calcularTotal() {
        String cantidad = txtCantidad.getText().toString();

        if (seleccionado == null || cantidad.isEmpty() || cantidad.length() > 6) {
            txtTotal.setText("");
            return;
        }

        int c = Integer.parseInt(cantidad);
        if (c <= 0) {
            txtTotal.setText("");
            return;
        }

        txtTotal.setText(String.format(Locale.US, "L %.2f", c * seleccionado.getPrecio()));
    }

    // ---------- VALIDAR ----------
    public boolean validar() {

        boolean retorno = true;

        String cantidad = txtCantidad.getText().toString();

        if (seleccionado == null) {
            txtBuscar.setError("PRIMERO BUSCA UN PRODUCTO");
            retorno = false;
        } else if (cantidad.isEmpty()) {
            txtCantidad.setError("LA CANTIDAD NO PUEDE ESTAR VACIA");
            retorno = false;
        } else if (cantidad.length() > 6) {
            txtCantidad.setError("CANTIDAD DEMASIADO GRANDE");
            retorno = false;
        } else if (Integer.parseInt(cantidad) <= 0) {
            txtCantidad.setError("LA CANTIDAD DEBE SER MAYOR QUE 0");
            retorno = false;
        }

        return retorno;
    }

    // ---------- AGREGAR ----------
    // Regresa el producto a Registrar_Ventas
    private void agregarProducto() {
        int cantidad = Integer.parseInt(txtCantidad.getText().toString());
        double total = cantidad * seleccionado.getPrecio();

        Intent resultado = new Intent();
        resultado.putExtra("producto_id", seleccionado.getId());
        resultado.putExtra("nombre", seleccionado.getNombre());
        resultado.putExtra("cantidad", cantidad);
        resultado.putExtra("precio", seleccionado.getPrecio());
        resultado.putExtra("total", total);
        setResult(RESULT_OK, resultado);
        finish();
    }

    private void cancelar() {
        setResult(RESULT_CANCELED);
        finish();
    }
}