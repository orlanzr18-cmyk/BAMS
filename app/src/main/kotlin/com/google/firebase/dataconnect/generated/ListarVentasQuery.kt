
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


public interface ListarVentasQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      ExampleConnector,
      ListarVentasQuery.Data,
      Unit
    >
{
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val ventas: List<VentasItem>,
  
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class VentasItem(
  
    val id: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
  
    val nombreVenta: String,
  
    val cantidad: Int,
  
    val precioHistorico: Double,
  
    val fecha: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.TimestampSerializer::class) com.google.firebase.Timestamp,
  
    val usuario: Usuario,
  
    val cliente: Cliente,
  
    val producto: Producto,
  
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class Usuario(
  
    val usuario: String,
  
  ) {
    
    
  }
      
        @kotlinx.serialization.Serializable
  public data class Cliente(
  
    val nombreCliente: String,
  
  ) {
    
    
  }
      
        @kotlinx.serialization.Serializable
  public data class Producto(
  
    val nombreProducto: String,
  
  ) {
    
    
  }
      
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "ListarVentas"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Unit> =
      kotlinx.serialization.serializer()
  }
}

public fun ListarVentasQuery.ref(
  
): com.google.firebase.dataconnect.QueryRef<
    ListarVentasQuery.Data,
    Unit
  > =
  ref(
    
      Unit
    
  )

public suspend fun ListarVentasQuery.execute(

  
    fetchPolicy: com.google.firebase.dataconnect.QueryRef.FetchPolicy = com.google.firebase.dataconnect.QueryRef.FetchPolicy.PREFER_CACHE,
  

  ): com.google.firebase.dataconnect.QueryResult<
    ListarVentasQuery.Data,
    Unit
  > =
  ref(
    
  ).execute(fetchPolicy = fetchPolicy)


  public fun ListarVentasQuery.flow(
    
    ): kotlinx.coroutines.flow.Flow<ListarVentasQuery.Data> =
    ref(
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }

