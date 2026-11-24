
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



public interface CrearInventarioMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CrearInventarioMutation.Data,
      CrearInventarioMutation.Variables
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
  
    val inventario_insert: InventarioKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CrearInventario"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CrearInventarioMutation.ref(
  
    productoId: java.util.UUID,cantidad: Int,

  
  
): com.google.firebase.dataconnect.MutationRef<
    CrearInventarioMutation.Data,
    CrearInventarioMutation.Variables
  > =
  ref(
    
      CrearInventarioMutation.Variables(
        productoId=productoId,cantidad=cantidad,
  
      )
    
  )

public suspend fun CrearInventarioMutation.execute(

  
    
      productoId: java.util.UUID,cantidad: Int,

  

  ): com.google.firebase.dataconnect.MutationResult<
    CrearInventarioMutation.Data,
    CrearInventarioMutation.Variables
  > =
  ref(
    
      productoId=productoId,cantidad=cantidad,
  
    
  ).execute()


