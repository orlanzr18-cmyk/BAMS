package com.example.bams

import com.google.firebase.dataconnect.generated.ExampleConnector
import com.google.firebase.dataconnect.generated.execute
import com.google.firebase.dataconnect.generated.instance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

data class ProductoItem(
    val id: String,
    val nombre: String,
    val descripcion: String?,
    val precio: Double,
    val codigoBarra: String?
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
}