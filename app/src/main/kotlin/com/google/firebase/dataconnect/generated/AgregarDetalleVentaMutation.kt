
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



public interface AgregarDetalleVentaMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      AgregarDetalleVentaMutation.Data,
      AgregarDetalleVentaMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val ventaId: Int,
  
    val productoId: Int,
  
    val cantidad: Int,
  
    val precioHistorico: Double,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val detalleVenta_insert: DetalleVentaKey,
  
    val inventario_update: InventarioKey?,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AgregarDetalleVenta"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AgregarDetalleVentaMutation.ref(
  
    ventaId: Int,productoId: Int,cantidad: Int,precioHistorico: Double,

  
  
): com.google.firebase.dataconnect.MutationRef<
    AgregarDetalleVentaMutation.Data,
    AgregarDetalleVentaMutation.Variables
  > =
  ref(
    
      AgregarDetalleVentaMutation.Variables(
        ventaId=ventaId,productoId=productoId,cantidad=cantidad,precioHistorico=precioHistorico,
  
      )
    
  )

public suspend fun AgregarDetalleVentaMutation.execute(

  
    
      ventaId: Int,productoId: Int,cantidad: Int,precioHistorico: Double,

  

  ): com.google.firebase.dataconnect.MutationResult<
    AgregarDetalleVentaMutation.Data,
    AgregarDetalleVentaMutation.Variables
  > =
  ref(
    
      ventaId=ventaId,productoId=productoId,cantidad=cantidad,precioHistorico=precioHistorico,
  
    
  ).execute()


