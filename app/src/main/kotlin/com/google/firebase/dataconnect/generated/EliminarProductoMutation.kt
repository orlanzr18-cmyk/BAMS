
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



public interface EliminarProductoMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      EliminarProductoMutation.Data,
      EliminarProductoMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val id: Int,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val producto_delete: ProductoKey?,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "EliminarProducto"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun EliminarProductoMutation.ref(
  
    id: Int,

  
  
): com.google.firebase.dataconnect.MutationRef<
    EliminarProductoMutation.Data,
    EliminarProductoMutation.Variables
  > =
  ref(
    
      EliminarProductoMutation.Variables(
        id=id,
  
      )
    
  )

public suspend fun EliminarProductoMutation.execute(

  
    
      id: Int,

  

  ): com.google.firebase.dataconnect.MutationResult<
    EliminarProductoMutation.Data,
    EliminarProductoMutation.Variables
  > =
  ref(
    
      id=id,
  
    
  ).execute()


