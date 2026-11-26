package com.example.bams;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Registrar_Ventas extends AppCompatActivity {

    EditText txtcliente;
    GridView gvProductosVenta;
    TextView tvSinProductos, tvTotalVenta;
    Button btnRegistrarVenta;
    Validacion val = new Validacion();

    ArrayList<LineaVenta> lineas = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();

    // Recibe el producto que se agregó en Agregar_Producto_Venta
    private final ActivityResultLauncher<Intent> lanzadorAgregar = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent d = result.getData();
                    agregarLinea(
                            d.getIntExtra("producto_id", -1),
                            d.getStringExtra("nombre"),
                            d.getIntExtra("cantidad", 0),
                            d.getDoubleExtra("precio", 0)
                    );
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_ventas);

        View raiz = findViewById(R.id.main);
        int pl = raiz.getPaddingLeft(), pt = raiz.getPaddingTop();
        int pr = raiz.getPaddingRight(), pb = raiz.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(raiz, (v, insets) -> {
            Insets barras = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(pl + barras.left, pt + barras.top, pr + barras.right, pb + barras.bottom);
            return insets;
        });

        txtcliente = findViewById(R.id.txtcliente);
        gvProductosVenta = findViewById(R.id.gvProductosVenta);
        tvSinProductos = findViewById(R.id.tvSinProductos);
        tvTotalVenta = findViewById(R.id.tvTotalVenta);
        btnRegistrarVenta = findViewById(R.id.btnRegistrarVenta);

        refrescarLista();

        // Tocar un producto de la lista para quitarlo
        gvProductosVenta.setOnItemClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(this)
                    .setTitle("Quitar producto")
                    .setMessage("¿Quitar " + nombres.get(position) + " de la venta?")
                    .setPositiveButton("Quitar", (dialog, which) -> {
                        lineas.remove(position);
                        nombres.remove(position);
                        refrescarLista();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    // ---------- BOTONES (onClick del XML) ----------
    public void volver(View v) {
        finish();
    }

    public void agregarProducto(View v) {
        lanzadorAgregar.launch(new Intent(this, Agregar_Producto_Venta.class));
    }

    public void registrarVenta(View v) {
        if (!validar()) {
            return;
        }

        String nombreCliente = txtcliente.getText().toString().trim();
        btnRegistrarVenta.setEnabled(false);

        obtenerClienteId(nombreCliente, new Resultado<Integer>() {
            @Override
            public void onExito(Integer clienteId) {
                guardarVenta(nombreCliente, clienteId);
            }

            @Override
            public void onError(String mensaje) {
                btnRegistrarVenta.setEnabled(true);
                Toast.makeText(Registrar_Ventas.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    // ---------- LISTA ----------
    private void agregarLinea(int productoId, String nombre, int cantidad, double precio) {
        // Si el producto ya está en la lista, se suma la cantidad
        for (int i = 0; i < lineas.size(); i++) {
            if (lineas.get(i).getProductoId() == productoId) {
                int nuevaCantidad = lineas.get(i).getCantidad() + cantidad;
                lineas.set(i, new LineaVenta(productoId, nuevaCantidad, precio));
                refrescarLista();
                return;
            }
        }
        lineas.add(new LineaVenta(productoId, cantidad, precio));
        nombres.add(nombre);
        refrescarLista();
    }

    private void refrescarLista() {
        ArrayList<String> textos = new ArrayList<>();
        double total = 0;

        for (int i = 0; i < lineas.size(); i++) {
            LineaVenta l = lineas.get(i);
            double subtotal = l.getCantidad() * l.getPrecio();
            total += subtotal;
            textos.add(nombres.get(i) + "\n"
                    + l.getCantidad() + " x L " + String.format(Locale.US, "%.2f", l.getPrecio())
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
        tvSinProductos.setVisibility(lineas.isEmpty() ? View.VISIBLE : View.GONE);
        tvTotalVenta.setText(String.format(Locale.US, "L %.2f", total));
    }

    // ---------- VALIDAR ----------
    public boolean validar() {

        boolean retorno = true;

        if (!val.vacio(txtcliente, "EL CLIENTE NO PUEDE ESTAR VACIO")) {
            retorno = false;
        }

        if (lineas.isEmpty()) {
            Toast.makeText(this, "AGREGA AL MENOS UN PRODUCTO", Toast.LENGTH_SHORT).show();
            retorno = false;
        }

        return retorno;
    }

    // ---------- GUARDAR ----------
    private void guardarVenta(String nombreCliente, int clienteId) {
        BamsDatos.registrarVenta("Venta - " + nombreCliente, clienteId, lineas, new Resultado<Boolean>() {
            @Override
            public void onExito(Boolean dato) {
                Toast.makeText(Registrar_Ventas.this, "Venta registrada", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(String mensaje) {
                btnRegistrarVenta.setEnabled(true);
                Toast.makeText(Registrar_Ventas.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    // ---------- CLIENTE (solo nombre) ----------
    // Si ya existe un cliente con ese nombre lo usa; si no, lo crea
    private void obtenerClienteId(String nombre, Resultado<Integer> cb) {
        BamsDatos.listarClientes(new Resultado<List<ClienteItem>>() {
            @Override
            public void onExito(List<ClienteItem> clientes) {
                for (ClienteItem c : clientes) {
                    if (c.getNombre().equalsIgnoreCase(nombre)) {
                        cb.onExito(c.getId());
                        return;
                    }
                }
                crearClienteNuevo(nombre, cb);
            }

            @Override
            public void onError(String mensaje) {
                cb.onError(mensaje);
            }
        });
    }

    private void crearClienteNuevo(String nombre, Resultado<Integer> cb) {
        BamsDatos.crearCliente(nombre, new Resultado<Boolean>() {
            @Override
            public void onExito(Boolean dato) {
                buscarIdDelCliente(nombre, cb);
            }

            @Override
            public void onError(String mensaje) {
                cb.onError(mensaje);
            }
        });
    }

    // crearCliente no devuelve el id, así que se busca el cliente recién creado
    private void buscarIdDelCliente(String nombre, Resultado<Integer> cb) {
        BamsDatos.listarClientes(new Resultado<List<ClienteItem>>() {
            @Override
            public void onExito(List<ClienteItem> clientes) {
                int idEncontrado = -1;
                for (ClienteItem c : clientes) {
                    if (c.getNombre().equalsIgnoreCase(nombre) && c.getId() > idEncontrado) {
                        idEncontrado = c.getId();
                    }
                }
                if (idEncontrado == -1) {
                    cb.onError("No se pudo obtener el cliente");
                } else {
                    cb.onExito(idEncontrado);
                }
            }

            @Override
            public void onError(String mensaje) {
                cb.onError(mensaje);
            }
        });
    }
}