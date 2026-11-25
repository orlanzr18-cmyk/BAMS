
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
  
    val productoId: Int,
  
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
  
    productoId: Int,cantidad: Int,

  
  
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

  
    
      productoId: Int,cantidad: Int,

  

  ): com.google.firebase.dataconnect.MutationResult<
    AjustarInventarioMutation.Data,
    AjustarInventarioMutation.Variables
  > =
  ref(
    
      productoId=productoId,cantidad=cantidad,
  
    
  ).execute()


