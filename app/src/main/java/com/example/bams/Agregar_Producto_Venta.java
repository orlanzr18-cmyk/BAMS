package com.example.bams;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Agregar_Producto_Venta extends AppCompatActivity {

    AutoCompleteTextView txtBuscar;
    EditText txtCantidad, txtprecio, txtTotal;
    TextView tvNombreProducto, tvStockProducto;

    List<ProductoItem> productos = new ArrayList<>();
    ProductoItem seleccionado = null;

    Validacion val = new Validacion();

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

        // Por ahora no se maneja stock
        tvStockProducto.setVisibility(View.GONE);

        // El lector de código de barras escribe en este campo
        txtBuscar.requestFocus();

        cargarSugerencias();
        txtBuscar.setOnClickListener(v -> txtBuscar.showDropDown());

        // Al tocar una sugerencia se selecciona ese producto
        txtBuscar.setOnItemClickListener((parent, view, position, id) -> {
            String nombre = (String) parent.getItemAtPosition(position);
            for (ProductoItem p : productos) {
                if (p.getNombre().equals(nombre)) {
                    mostrarProducto(p);
                    break;
                }
            }
        });

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
    }

    // ---------- BOTONES (onClick del XML) ----------
    public void volver(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void buscar(View v) {
        buscarProducto();
    }

    public void agregar(View v) {
        if (validar()) {
            agregarProducto();
        }
    }

    // ---------- SUGERENCIAS ----------
    // Carga los productos de la base de datos para la lista que sale al escribir
    private void cargarSugerencias() {
        BamsDatos.listarProductos(new Resultado<List<ProductoItem>>() {
            @Override
            public void onExito(List<ProductoItem> dato) {
                productos = dato;

                ArrayList<String> nombres = new ArrayList<>();
                for (ProductoItem p : dato) {
                    nombres.add(p.getNombre());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        Agregar_Producto_Venta.this,
                        android.R.layout.simple_dropdown_item_1line, nombres) {
                    @NonNull
                    @Override
                    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                        TextView t = (TextView) super.getView(position, convertView, parent);
                        t.setTextColor(Color.parseColor("#072C5A"));
                        return t;
                    }
                };

                txtBuscar.setAdapter(adapter);
            }

            @Override
            public void onError(String mensaje) {
                Toast.makeText(Agregar_Producto_Venta.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    // ---------- BUSCAR ----------
    private void buscarProducto() {
        if (!val.vacio(txtBuscar, "EL CAMPO BUSCAR NO PUEDE ESTAR VACIO")) {
            return;
        }

        String texto = txtBuscar.getText().toString().trim();

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
        for (ProductoItem p : productos) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                mostrarProducto(p);
                return;
            }
        }
        limpiarSeleccion();
        Toast.makeText(this, "No se encontró ningún producto", Toast.LENGTH_SHORT).show();
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

        if (!val.seleccionado(txtBuscar, seleccionado, "PRIMERO BUSCA UN PRODUCTO")) {
            retorno = false;
        }

        if (!val.mayorACero(txtCantidad, "LA CANTIDAD NO PUEDE ESTAR VACIA", "LA CANTIDAD DEBE SER MAYOR QUE 0")) {
            retorno = false;
        } else if (!val.maximoDigitos(txtCantidad, 6, "CANTIDAD DEMASIADO GRANDE")) {
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
}