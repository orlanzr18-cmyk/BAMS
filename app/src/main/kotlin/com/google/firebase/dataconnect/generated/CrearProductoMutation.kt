
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



public interface CrearProductoMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CrearProductoMutation.Data,
      CrearProductoMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val nombreProducto: String,
  
    val descripcionProducto: com.google.firebase.dataconnect.OptionalVariable<String?>,
  
    val precioProducto: Double,
  
    val codigoBarra: com.google.firebase.dataconnect.OptionalVariable<String?>,
  
  ) {
    
    
      
      @kotlin.DslMarker public annotation class BuilderDsl

      
      @BuilderDsl
      public interface Builder {
        public var nombreProducto: String
        public var descripcionProducto: String?
        public var precioProducto: Double
        public var codigoBarra: String?
        
      }

      public companion object {
        
        @Suppress("NAME_SHADOWING")
        public fun build(
          nombreProducto: String,precioProducto: Double,
          block_: Builder.() -> Unit
        ): Variables {
          var nombreProducto= nombreProducto
            var descripcionProducto: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            var precioProducto= precioProducto
            var codigoBarra: com.google.firebase.dataconnect.OptionalVariable<String?> =
                com.google.firebase.dataconnect.OptionalVariable.Undefined
            

          return object : Builder {
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
              nombreProducto=nombreProducto,descripcionProducto=descripcionProducto,precioProducto=precioProducto,codigoBarra=codigoBarra,
            )
          }
        }
      }
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val producto_insert: ProductoKey,
  
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CrearProducto"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CrearProductoMutation.ref(
  
    nombreProducto: String,precioProducto: Double,

  
    block_: CrearProductoMutation.Variables.Builder.() -> Unit = {}
  
): com.google.firebase.dataconnect.MutationRef<
    CrearProductoMutation.Data,
    CrearProductoMutation.Variables
  > =
  ref(
    
      CrearProductoMutation.Variables.build(
        nombreProducto=nombreProducto,precioProducto=precioProducto,
  
    block_
      )
    
  )

public suspend fun CrearProductoMutation.execute(

  
    
      nombreProducto: String,precioProducto: Double,

  
    block_: CrearProductoMutation.Variables.Builder.() -> Unit = {}

  ): com.google.firebase.dataconnect.MutationResult<
    CrearProductoMutation.Data,
    CrearProductoMutation.Variables
  > =
  ref(
    
      nombreProducto=nombreProducto,precioProducto=precioProducto,
  
    block_
    
  ).execute()


