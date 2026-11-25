
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


import kotlinx.coroutines.flow.filterNotNull as _flow_filterNotNull
import kotlinx.coroutines.flow.map as _flow_map


public interface BuscarProductoPorCodigoQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      ExampleConnector,
      BuscarProductoPorCodigoQuery.Data,
      BuscarProductoPorCodigoQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val codigoBarra: String,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val productos: List<ProductosItem>,
  
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class ProductosItem(
  
    val id: Int,
  
    val nombreProducto: String,
  
    val descripcionProducto: String?,
  
    val precioProducto: Double,
  
    val codigoBarra: String?,
  
  ) {
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "BuscarProductoPorCodigo"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun BuscarProductoPorCodigoQuery.ref(
  
    codigoBarra: String,

  
  
): com.google.firebase.dataconnect.QueryRef<
    BuscarProductoPorCodigoQuery.Data,
    BuscarProductoPorCodigoQuery.Variables
  > =
  ref(
    
      BuscarProductoPorCodigoQuery.Variables(
        codigoBarra=codigoBarra,
  
      )
    
  )

public suspend fun BuscarProductoPorCodigoQuery.execute(

  
    
      codigoBarra: String,
  fetchPolicy: com.google.firebase.dataconnect.QueryRef.FetchPolicy = com.google.firebase.dataconnect.QueryRef.FetchPolicy.PREFER_CACHE,
  

  ): com.google.firebase.dataconnect.QueryResult<
    BuscarProductoPorCodigoQuery.Data,
    BuscarProductoPorCodigoQuery.Variables
  > =
  ref(
    
      codigoBarra=codigoBarra,
  
    
  ).execute(fetchPolicy = fetchPolicy)


  public fun BuscarProductoPorCodigoQuery.flow(
    
      codigoBarra: String,

  
    
    ): kotlinx.coroutines.flow.Flow<BuscarProductoPorCodigoQuery.Data> =
    ref(
        
          codigoBarra=codigoBarra,
  
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }

