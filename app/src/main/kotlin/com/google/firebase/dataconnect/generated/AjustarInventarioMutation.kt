
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



public interface AjustarInventarioMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      AjustarInventarioMutation.Data,
      AjustarInventarioMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val productoId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
    val cantidad: Int,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val inventario_update: InventarioKey?,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AjustarInventario"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AjustarInventarioMutation.ref(
  
    productoId: java.util.UUID,cantidad: Int,

  
  
): com.google.firebase.dataconnect.MutationRef<
    AjustarInventarioMutation.Data,
    AjustarInventarioMutation.Variables
  > =
  ref(
    
      AjustarInventarioMutation.Variables(
        productoId=productoId,cantidad=cantidad,
  
      )
    
  )

public suspend fun AjustarInventarioMutation.execute(

  
    
      productoId: java.util.UUID,cantidad: Int,

  

  ): com.google.firebase.dataconnect.MutationResult<
    AjustarInventarioMutation.Data,
    AjustarInventarioMutation.Variables
  > =
  ref(
    
      productoId=productoId,cantidad=cantidad,
  
    
  ).execute()


