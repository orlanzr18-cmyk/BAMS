
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



public interface ActualizarProductoMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      ActualizarProductoMutation.Data,
      ActualizarProductoMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val id: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
    val nombreProducto: String,
  
    val descripcionProducto: com.google.firebase.dataconnect.OptionalVariable<String?>,
  
    val precioProducto: Double,
  
    val codigoBarra: com.google.firebase.dataconnect.OptionalVariable<String?>,
  
  ) {
    
    
      
      @kotlin.DslMarker public annotation class BuilderDsl

      
      @BuilderDsl
      public interface Builder {
        public var id: java.util.UUID
        public var nombreProducto: String
        public var descripcionProducto: String?
        public var precioProducto: Double
        public var codigoBarra: String?
        
      }

      public companion object {
        
        @Suppress("NAME_SHADOWING")
        public fun build(
          id: java.util.UUID,nombreProducto: String,precioProducto: Double,
          block_: Builder.() -> Unit
        ): Variables {
          var id= id
            var nombreProducto= nombreProducto
            var descripcionProducto: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var precioProducto= precioProducto
            var codigoBarra: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            

          return object : Builder {
            override var id: java.util.UUID
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { id = value_ }
              
            override var nombreProducto: String
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { nombreProducto = value_ }
              
            override var descripcionProducto: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { descripcionProducto = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            override var precioProducto: Double
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { precioProducto = value_ }
              
            override var codigoBarra: String?
              get() = throw UnsupportedOperationException("getting builder values is not supported")
              set(value_) { codigoBarra = com.google.firebase.dataconnect.OptionalVariable.Value(value_) }
              
            
          }.apply(block_)
          .let {
            Variables(
              id=id,nombreProducto=nombreProducto,descripcionProducto=descripcionProducto,precioProducto=precioProducto,codigoBarra=codigoBarra,
            )
          }
        }
      }
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val producto_update: ProductoKey?,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "ActualizarProducto"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun ActualizarProductoMutation.ref(
  
    id: java.util.UUID,nombreProducto: String,precioProducto: Double,

  
    block_: ActualizarProductoMutation.Variables.Builder.() -> Unit = {}
  
): com.google.firebase.dataconnect.MutationRef<
    ActualizarProductoMutation.Data,
    ActualizarProductoMutation.Variables
  > =
  ref(
    
      ActualizarProductoMutation.Variables.build(
        id=id,nombreProducto=nombreProducto,precioProducto=precioProducto,
  
    block_
      )
    
  )

public suspend fun ActualizarProductoMutation.execute(

  
    
      id: java.util.UUID,nombreProducto: String,precioProducto: Double,

  
    block_: ActualizarProductoMutation.Variables.Builder.() -> Unit = {}

  ): com.google.firebase.dataconnect.MutationResult<
    ActualizarProductoMutation.Data,
    ActualizarProductoMutation.Variables
  > =
  ref(
    
      id=id,nombreProducto=nombreProducto,precioProducto=precioProducto,
  
    block_
    
  ).execute()


