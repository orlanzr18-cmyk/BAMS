package com.example.bams

import com.google.firebase.dataconnect.generated.ExampleConnector
import com.google.firebase.dataconnect.generated.execute
import com.google.firebase.dataconnect.generated.instance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.UUID

data class ProductoItem(
    val id: String,
    val nombre: String,
    val descripcion: String?,
    val precio: Double,
    val codigoBarra: String?
)

data class InventarioItem(
    val id: String,
    val productoId: String,
    val nombreProducto: String,
    val codigoBarra: String?,
    val cantidad: Int
)

interface Resultado<T> {
    fun onExito(dato: T)
    fun onError(mensaje: String)
}

object BamsDatos {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val conector get() = ExampleConnector.instance

    @JvmStatic
    fun listarProductos(cb: Resultado<List<ProductoItem>>) {
        scope.launch {
            try {
                val data = conector.listarProductos.execute().data
                cb.onExito(data.productos.map {
                    ProductoItem(
                        it.id.toString(),
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

    @JvmStatic
    fun listarInventario(cb: Resultado<List<InventarioItem>>) {
        scope.launch {
            try {
                val data = conector.listarInventario.execute().data
                cb.onExito(data.inventarios.map {
                    InventarioItem(
                        it.id.toString(),
                        it.producto.id.toString(),
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
    fun crearInventario(productoId: String, cantidad: Int, cb: Resultado<Boolean>) {
        scope.launch {
            try {
                conector.crearInventario.execute(
                    productoId = UUID.fromString(productoId),
                    cantidad = cantidad
                )
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al crear inventario")
            }
        }
    }

    @JvmStatic
    fun ajustarInventario(productoId: String, cantidad: Int, cb: Resultado<Boolean>) {
        scope.launch {
            try {
                conector.ajustarInventario.execute(
                    productoId = UUID.fromString(productoId),
                    cantidad = cantidad
                )
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al ajustar inventario")
            }
        }
    }

    @JvmStatic
    fun crearVenta(
        nombreVenta: String,
        clienteId: String,
        productoId: String,
        cantidad: Int,
        precioHistorico: Double,
        cb: Resultado<Boolean>
    ) {
        scope.launch {
            try {
                conector.crearVenta.execute(
                    nombreVenta = nombreVenta,
                    clienteId = UUID.fromString(clienteId),
                    productoId = UUID.fromString(productoId),
                    cantidad = cantidad,
                    precioHistorico = precioHistorico
                )
                cb.onExito(true)
            } catch (e: Exception) {
                cb.onError(e.message ?: "Error al registrar la venta")
            }
        }
    }
}