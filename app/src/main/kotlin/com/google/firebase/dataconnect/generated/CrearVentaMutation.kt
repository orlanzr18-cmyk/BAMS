
@file:Suppress(
  "KotlinRedundantDiagnosticSuppress",
  "PropertyName",
  "MayBeConstant",
  "RedundantVisibilityModifier",
  "RedundantCompanionReference",
  "RemoveEmptyClassBody",
  "SpellCheckingInspection",
  "unused",
)

package com.google.firebase.dataconnect.generated



public interface CrearVentaMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CrearVentaMutation.Data,
      CrearVentaMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val nombreVenta: String,
  
    val clienteId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
    val productoId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
    val cantidad: Int,
  
    val precioHistorico: Double,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val venta_insert: VentaKey,
  
    val inventario_update: InventarioKey?,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CrearVenta"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CrearVentaMutation.ref(
  
    nombreVenta: String,clienteId: java.util.UUID,productoId: java.util.UUID,cantidad: Int,precioHistorico: Double,

  
  
): com.google.firebase.dataconnect.MutationRef<
    CrearVentaMutation.Data,
    CrearVentaMutation.Variables
  > =
  ref(
    
      CrearVentaMutation.Variables(
        nombreVenta=nombreVenta,clienteId=clienteId,productoId=productoId,cantidad=cantidad,precioHistorico=precioHistorico,
  
      )
    
  )

public suspend fun CrearVentaMutation.execute(

  
    
      nombreVenta: String,clienteId: java.util.UUID,productoId: java.util.UUID,cantidad: Int,precioHistorico: Double,

  

  ): com.google.firebase.dataconnect.MutationResult<
    CrearVentaMutation.Data,
    CrearVentaMutation.Variables
  > =
  ref(
    
      nombreVenta=nombreVenta,clienteId=clienteId,productoId=productoId,cantidad=cantidad,precioHistorico=precioHistorico,
  
    
  ).execute()


