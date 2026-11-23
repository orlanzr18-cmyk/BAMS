
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



public interface CrearUsuarioMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CrearUsuarioMutation.Data,
      CrearUsuarioMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val usuario: String,
  
    val correo: String,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val usuario_insert: UsuarioKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CrearUsuario"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CrearUsuarioMutation.ref(
  
    usuario: String,correo: String,

  
  
): com.google.firebase.dataconnect.MutationRef<
    CrearUsuarioMutation.Data,
    CrearUsuarioMutation.Variables
  > =
  ref(
    
      CrearUsuarioMutation.Variables(
        usuario=usuario,correo=correo,
  
      )
    
  )

public suspend fun CrearUsuarioMutation.execute(

  
    
      usuario: String,correo: String,

  

  ): com.google.firebase.dataconnect.MutationResult<
    CrearUsuarioMutation.Data,
    CrearUsuarioMutation.Variables
  > =
  ref(
    
      usuario=usuario,correo=correo,
  
    
  ).execute()


