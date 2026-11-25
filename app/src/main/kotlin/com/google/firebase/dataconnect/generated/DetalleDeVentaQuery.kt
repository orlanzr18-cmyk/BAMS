
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


public interface DetalleDeVentaQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      ExampleConnector,
      DetalleDeVentaQuery.Data,
      DetalleDeVentaQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val ventaId: Int,
  
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val detalleVentas: List<DetalleVentasItem>,
  
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class DetalleVentasItem(
  
    val id: Int,
  
    val cantidad: Int,
  
    val precioHistorico: Double,
  
    val producto: Producto,
  
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class Producto(
  
    val id: Int,
  
    val nombreProducto: String,
  
  ) {
    
    
  }
      
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "DetalleDeVenta"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun DetalleDeVentaQuery.ref(
  
    ventaId: Int,

  
  
): com.google.firebase.dataconnect.QueryRef<
    DetalleDeVentaQuery.Data,
    DetalleDeVentaQuery.Variables
  > =
  ref(
    
      DetalleDeVentaQuery.Variables(
        ventaId=ventaId,
  
      )
    
  )

public suspend fun DetalleDeVentaQuery.execute(

  
    
      ventaId: Int,
  fetchPolicy: com.google.firebase.dataconnect.QueryRef.FetchPolicy = com.google.firebase.dataconnect.QueryRef.FetchPolicy.PREFER_CACHE,
  

  ): com.google.firebase.dataconnect.QueryResult<
    DetalleDeVentaQuery.Data,
    DetalleDeVentaQuery.Variables
  > =
  ref(
    
      ventaId=ventaId,
  
    
  ).execute(fetchPolicy = fetchPolicy)


  public fun DetalleDeVentaQuery.flow(
    
      ventaId: Int,

  
    
    ): kotlinx.coroutines.flow.Flow<DetalleDeVentaQuery.Data> =
    ref(
        
          ventaId=ventaId,
  
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }

