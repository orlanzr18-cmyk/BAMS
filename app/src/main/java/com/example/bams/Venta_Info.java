package com.example.bams;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Venta_Info extends AppCompatActivity {

    TextView tvNumeroVenta, tvCliente, tvFecha, tvTotalVenta;
    GridView gvProductosVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_info);

        View raiz = findViewById(R.id.main);
        int pl = raiz.getPaddingLeft(), pt = raiz.getPaddingTop();
        int pr = raiz.getPaddingRight(), pb = raiz.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(raiz, (v, insets) -> {
            Insets barras = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(pl + barras.left, pt + barras.top, pr + barras.right, pb + barras.bottom);
            return insets;
        });

        tvNumeroVenta = findViewById(R.id.tvNumeroVenta);
        tvCliente = findViewById(R.id.tvCliente);
        tvFecha = findViewById(R.id.tvFecha);
        tvTotalVenta = findViewById(R.id.tvTotalVenta);
        gvProductosVenta = findViewById(R.id.gvProductosVenta);

        // Datos que manda Ver_Ventas
        int ventaId = getIntent().getIntExtra("venta_id", -1);
        String cliente = getIntent().getStringExtra("cliente");
        String fecha = getIntent().getStringExtra("fecha");
        double total = getIntent().getDoubleExtra("total", 0);

        if (ventaId == -1) {
            Toast.makeText(this, "No se encontró la venta", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvNumeroVenta.setText("Venta #" + ventaId);
        tvCliente.setText("Cliente: " + (cliente == null ? "-" : cliente));
        tvFecha.setText("Fecha: " + (fecha == null ? "-" : fecha));
        tvTotalVenta.setText(String.format(Locale.US, "L %.2f", total));

        cargarDetalle(ventaId);
    }

    // ---------- BOTONES (onClick del XML) ----------
    public void volver(View v) {
        finish();
    }

    // ---------- PRODUCTOS DE LA VENTA ----------
    private void cargarDetalle(int ventaId) {
        BamsDatos.detalleDeVenta(ventaId, new Resultado<List<DetalleItem>>() {
            @Override
            public void onExito(List<DetalleItem> detalle) {
                mostrarDetalle(detalle);
            }

            @Override
            public void onError(String mensaje) {
                Toast.makeText(Venta_Info.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrarDetalle(List<DetalleItem> detalle) {
        ArrayList<String> textos = new ArrayList<>();

        for (DetalleItem d : detalle) {
            double subtotal = d.getCantidad() * d.getPrecioHistorico();
            textos.add(d.getNombreProducto() + "\n"
                    + d.getCantidad() + " x L " + String.format(Locale.US, "%.2f", d.getPrecioHistorico())
                    + " = L " + String.format(Locale.US, "%.2f", subtotal));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, textos) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                TextView t = (TextView) super.getView(position, convertView, parent);
                t.setTextColor(Color.parseColor("#072C5A"));
                return t;
            }
        };

        gvProductosVenta.setAdapter(adapter);
    }
}