
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



public interface CrearClienteMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CrearClienteMutation.Data,
      CrearClienteMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val nombreCliente: String,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val cliente_insert: ClienteKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CrearCliente"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CrearClienteMutation.ref(
  
    nombreCliente: String,

  
  
): com.google.firebase.dataconnect.MutationRef<
    CrearClienteMutation.Data,
    CrearClienteMutation.Variables
  > =
  ref(
    
      CrearClienteMutation.Variables(
        nombreCliente=nombreCliente,
  
      )
    
  )

public suspend fun CrearClienteMutation.execute(

  
    
      nombreCliente: String,

  

  ): com.google.firebase.dataconnect.MutationResult<
    CrearClienteMutation.Data,
    CrearClienteMutation.Variables
  > =
  ref(
    
      nombreCliente=nombreCliente,
  
    
  ).execute()


