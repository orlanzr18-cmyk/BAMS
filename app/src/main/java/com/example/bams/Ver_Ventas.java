package com.example.bams;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Ver_Ventas extends AppCompatActivity {

    GridView gvVentas;
    TextView tvSinVentas, tvTotalProductos, tvTotalGanado;

    ArrayList<VentaItem> ventas = new ArrayList<>();
    int totalProductos = 0;
    int pendientes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ventas);

        gvVentas = findViewById(R.id.gvVentas);
        tvSinVentas = findViewById(R.id.tvSinVentas);
        tvTotalProductos = findViewById(R.id.tvTotalProductos);
        tvTotalGanado = findViewById(R.id.tvTotalGanado);

        // Tocar una venta abre el detalle
        gvVentas.setOnItemClickListener((parent, view, position, id) -> {
            VentaItem v = ventas.get(position);

            Intent i = new Intent(this, Venta_Info.class);
            i.putExtra("venta_id", v.getId());
            i.putExtra("nombre_venta", v.getNombreVenta());
            i.putExtra("cliente", v.getCliente());
            i.putExtra("fecha", formatoFecha(v.getFechaMillis()));
            i.putExtra("total", v.getTotal());
            startActivity(i);
        });

        cargarVentas();
    }

    // ---------- BOTONES (onClick del XML) ----------
    public void volver(View v) {
        finish();
    }

    // ---------- CARGAR ----------
    private void cargarVentas() {
        BamsDatos.listarVentas(new Resultado<List<VentaItem>>() {
            @Override
            public void onExito(List<VentaItem> dato) {
                ventas = new ArrayList<>(dato);

                // Las más recientes primero
                Collections.sort(ventas, (a, b) -> Long.compare(b.getFechaMillis(), a.getFechaMillis()));

                mostrarVentas();
                contarProductos();
            }

            @Override
            public void onError(String mensaje) {
                Toast.makeText(Ver_Ventas.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrarVentas() {
        ArrayList<String> textos = new ArrayList<>();
        double totalGanado = 0;

        for (VentaItem v : ventas) {
            totalGanado += v.getTotal();
            textos.add("Venta #" + v.getId() + " - " + v.getCliente() + "\n"
                    + formatoFecha(v.getFechaMillis()) + "  |  L "
                    + String.format(Locale.US, "%.2f", v.getTotal()));
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

        gvVentas.setAdapter(adapter);
        tvSinVentas.setVisibility(ventas.isEmpty() ? View.VISIBLE : View.GONE);
        tvTotalGanado.setText(String.format(Locale.US, "L %.2f", totalGanado));
        tvTotalProductos.setText("0");
    }

    // Suma las cantidades de todos los productos de todas las ventas
    private void contarProductos() {
        totalProductos = 0;
        pendientes = ventas.size();

        for (VentaItem v : ventas) {
            BamsDatos.detalleDeVenta(v.getId(), new Resultado<List<DetalleItem>>() {
                @Override
                public void onExito(List<DetalleItem> detalle) {
                    for (DetalleItem d : detalle) {
                        totalProductos += d.getCantidad();
                    }
                    pendientes--;
                    tvTotalProductos.setText(String.valueOf(totalProductos));
                }

                @Override
                public void onError(String mensaje) {
                    pendientes--;
                }
            });
        }
    }

    private String formatoFecha(long millis) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date(millis));
    }
}