package com.example.bams;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class Modificar_Producto extends AppCompatActivity {

    private int productoId;
    private boolean tieneInventario = false;

    private EditText txtCodigo;
    private EditText txtNombre;
    private EditText txtDesc;
    private EditText txtPrecio;
    private EditText txtStock;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_producto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtCodigo = findViewById(R.id.txtcodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtDesc = findViewById(R.id.txtDesc);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtStock = findViewById(R.id.txtStock);
        btnGuardar = findViewById(R.id.btnGuardarCambios);

        productoId = getIntent().getIntExtra("id", -1);
        if (productoId == -1) {
            Toast.makeText(this, "No se recibio el producto", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnGuardar.setEnabled(false);

        BamsDatos.listarProductos(new Resultado<List<ProductoItem>>() {
            @Override
            public void onExito(List<ProductoItem> productos) {
                for (ProductoItem p : productos) {
                    if (p.getId() == productoId) {
                        txtCodigo.setText(p.getCodigoBarra());
                        txtNombre.setText(p.getNombre());
                        txtDesc.setText(p.getDescripcion());
                        txtPrecio.setText(String.valueOf(p.getPrecio()));
                    }
                }

                BamsDatos.listarInventario(new Resultado<List<InventarioItem>>() {
                    @Override
                    public void onExito(List<InventarioItem> inventario) {
                        for (InventarioItem i : inventario) {
                            if (i.getProductoId() == productoId) {
                                tieneInventario = true;
                                txtStock.setText(String.valueOf(i.getCantidad()));
                            }
                        }
                        btnGuardar.setEnabled(true);
                    }

                    @Override
                    public void onError(String mensaje) {
                        Toast.makeText(Modificar_Producto.this, mensaje, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onError(String mensaje) {
                Toast.makeText(Modificar_Producto.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void volver(View v) {
        finish();
    }

    public void cancelar(View v) {
        finish();
    }

    public void guardarCambios(View v) {
        String codigo = txtCodigo.getText().toString().trim();
        String nombre = txtNombre.getText().toString().trim();
        String desc = txtDesc.getText().toString().trim();
        String precioTxt = txtPrecio.getText().toString().trim();
        String stockTxt = txtStock.getText().toString().trim();

        if (nombre.isEmpty() || precioTxt.isEmpty() || stockTxt.isEmpty()) {
            Toast.makeText(this, "Nombre, precio y stock son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio;
        int stock;
        try {
            precio = Double.parseDouble(precioTxt);
            stock = Integer.parseInt(stockTxt);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Precio o stock invalido", Toast.LENGTH_SHORT).show();
            return;
        }

        btnGuardar.setEnabled(false);

        Resultado<Boolean> alTerminar = new Resultado<Boolean>() {
            @Override
            public void onExito(Boolean dato) {
                Toast.makeText(Modificar_Producto.this, "Producto actualizado", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onError(String mensaje) {
                btnGuardar.setEnabled(true);
                Toast.makeText(Modificar_Producto.this, mensaje, Toast.LENGTH_LONG).show();
            }
        };

        BamsDatos.actualizarProducto(
                productoId,
                nombre,
                desc.isEmpty() ? null : desc,
                precio,
                codigo.isEmpty() ? null : codigo,
                new Resultado<Boolean>() {
                    @Override
                    public void onExito(Boolean dato) {
                        if (tieneInventario) {
                            BamsDatos.ajustarInventario(productoId, stock, alTerminar);
                        } else {
                            BamsDatos.crearInventario(productoId, stock, alTerminar);
                        }
                    }

                    @Override
                    public void onError(String mensaje) {
                        btnGuardar.setEnabled(true);
                        Toast.makeText(Modificar_Producto.this, mensaje, Toast.LENGTH_LONG).show();
                    }
                });
    }
}