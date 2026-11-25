package com.example.bams

import com.google.firebase.dataconnect.generated.ExampleConnector
import com.google.firebase.dataconnect.generated.execute
import com.google.firebase.dataconnect.generated.instance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

data class ProductoItem(
    val id: Int,
    val nombre: String,
    val descripcion: String?,
    val precio: Double,
    val codigoBarra: String?
)

data class ClienteItem(
    val id: Int,
    val nombre: String
)

data class InventarioItem(
    val id: Int,
    val productoId: Int,
    val nombreProducto: String,
    val codigoBarra: String?,
    val cantidad: Int
)

data class LineaVenta(
    val productoId: Int,
    val cantidad: Int,
    val precio: Double
)

data class VentaItem(
    val id: Int,
    val nombreVenta: String,
    val total: Double,
    val fechaMillis: Long,
    val usuario: String,
    val cliente: String
)

data class DetalleItem(
    val productoId: Int,
    val nombreProducto: String,
    val cantidad: Int,
    val precioHistorico: Double
)

interface Resultado<T> {
    fun onExito(dato: T)
    fun onError(mensaje: String)
}

object BamsDatos {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val conector get() = ExampleConnector.instance

    // ---------------- PRODUCTOS ----------------

    @JvmStatic
    fun listarProductos(cb: Resultado<List<ProductoItem>>) {
        scope.launch {
            try {
                val data = conector.listarProductos.execute().data
                cb.onExito(data.productos.map {
                    ProductoItem(
                        it.id,
                        it.nombreProducto,
                        it.descripcionProducto,
                        it.precioProducto,
                        it.codigoBarra
                    )
                })
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al listar productos")
            }
        }
    }

    @JvmStatic
    fun buscarProductoPorCodigo(codigoBarra: String, cb: Resultado<List<ProductoItem>>) {
        scope.launch {
            try {
                val data = conector.buscarProductoPorCodigo
                    .execute(codigoBarra = codigoBarra).data
                cb.onExito(data.productos.map {
                    ProductoItem(
                        it.id,
                        it.nombreProducto,
                        it.descripcionProducto,
                        it.precioProducto,
                        it.codigoBarra
                    )
                })
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al buscar el producto")
            }
        }
    }

    @JvmStatic
    fun crearProducto(
        nombre: String,
        descripcion: String?,
        precio: Double,
        codigoBarra: String?,
        cb: Resultado<Boolean>
    ) {
        scope.launch {
            try {
                conector.crearProducto.execute(
                    nombreProducto = nombre,
                    precioProducto = precio
                ) {
                    descripcionProducto = descripcion
                    this.codigoBarra = codigoBarra
                }
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al crear producto")
            }
        }
    }

    @JvmStatic
    fun actualizarProducto(
        id: Int,
        nombre: String,
        descripcion: String?,
        precio: Double,
        codigoBarra: String?,
        cb: Resultado<Boolean>
    ) {
        scope.launch {
            try {
                conector.actualizarProducto.execute(
                    id = id,
                    nombreProducto = nombre,
                    precioProducto = precio
                ) {
                    descripcionProducto = descripcion
                    this.codigoBarra = codigoBarra
                }
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al actualizar producto")
            }
        }
    }

    @JvmStatic
    fun eliminarProducto(id: Int, cb: Resultado<Boolean>) {
        scope.launch {
            try {
                conector.eliminarProducto.execute(id = id)
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al eliminar producto")
            }
        }
    }

    // ---------------- CLIENTES ----------------

    @JvmStatic
    fun listarClientes(cb: Resultado<List<ClienteItem>>) {
        scope.launch {
            try {
                val data = conector.listarClientes.execute().data
                cb.onExito(data.clientes.map {
                    ClienteItem(it.id, it.nombreCliente)
                })
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al listar clientes")
            }
        }
    }

    @JvmStatic
    fun crearCliente(nombre: String, cb: Resultado<Boolean>) {
        scope.launch {
            try {
                conector.crearCliente.execute(nombreCliente = nombre)
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al crear cliente")
            }
        }
    }

    // ---------------- USUARIO ----------------

    @JvmStatic
    fun crearUsuario(usuario: String, correo: String, cb: Resultado<Boolean>) {
        scope.launch {
            try {
                conector.crearUsuario.execute(usuario = usuario, correo = correo)
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al crear usuario")
            }
        }
    }

    // ---------------- INVENTARIO ----------------

    @JvmStatic
    fun listarInventario(cb: Resultado<List<InventarioItem>>) {
        scope.launch {
            try {
                val data = conector.listarInventario.execute().data
                cb.onExito(data.inventarios.map {
                    InventarioItem(
                        it.id,
                        it.producto.id,
                        it.producto.nombreProducto,
                        it.producto.codigoBarra,
                        it.cantidad
                    )
                })
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al listar inventario")
            }
        }
    }

    @JvmStatic
    fun crearInventario(productoId: Int, cantidad: Int, cb: Resultado<Boolean>) {
        scope.launch {
            try {
                conector.crearInventario.execute(
                    productoId = productoId,
                    cantidad = cantidad
                )
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al crear inventario")
            }
        }
    }

    @JvmStatic
    fun ajustarInventario(productoId: Int, cantidad: Int, cb: Resultado<Boolean>) {
        scope.launch {
            try {
                conector.ajustarInventario.execute(
                    productoId = productoId,
                    cantidad = cantidad
                )
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al ajustar inventario")
            }
        }
    }

    // ---------------- VENTAS ----------------

    @JvmStatic
    fun registrarVenta(
        nombreVenta: String,
        clienteId: Int,
        lineas: List<LineaVenta>,
        cb: Resultado<Boolean>
    ) {
        scope.launch {
            try {
                val total = lineas.sumOf { it.cantidad * it.precio }
                val ventaId = conector.crearVenta.execute(
                    nombreVenta = nombreVenta,
                    clienteId = clienteId,
                    total = total
                ).data.venta_insert.id

                for (l in lineas) {
                    conector.agregarDetalleVenta.execute(
                        ventaId = ventaId,
                        productoId = l.productoId,
                        cantidad = l.cantidad,
                        precioHistorico = l.precio
                    )
                }
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al registrar la venta")
            }
        }
    }

    @JvmStatic
    fun listarVentas(cb: Resultado<List<VentaItem>>) {
        scope.launch {
            try {
                val data = conector.listarVentas.execute().data
                cb.onExito(data.ventas.map {
                    VentaItem(
                        it.id,
                        it.nombreVenta,
                        it.total,
                        it.fecha.toDate().time,
                        it.usuario.usuario,
                        it.cliente.nombreCliente
                    )
                })
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al listar ventas")
            }
        }
    }

    @JvmStatic
    fun detalleDeVenta(ventaId: Int, cb: Resultado<List<DetalleItem>>) {
        scope.launch {
            try {
                val data = conector.detalleDeVenta.execute(ventaId = ventaId).data
                cb.onExito(data.detalleVentas.map {
                    DetalleItem(
                        it.producto.id,
                        it.producto.nombreProducto,
                        it.cantidad,
                        it.precioHistorico
                    )
                })
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al cargar el detalle")
            }
        }
    }
}