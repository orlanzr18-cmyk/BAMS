
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

import com.google.firebase.dataconnect.getInstance as _fdcGetInstance
import kotlin.time.Duration.Companion.milliseconds as _milliseconds

public interface ExampleConnector : com.google.firebase.dataconnect.generated.GeneratedConnector<ExampleConnector> {
  override val dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect

  
    public val actualizarProducto: ActualizarProductoMutation
  
    public val ajustarInventario: AjustarInventarioMutation
  
    public val buscarProductoPorCodigo: BuscarProductoPorCodigoQuery
  
    public val crearCliente: CrearClienteMutation
  
    public val crearInventario: CrearInventarioMutation
  
    public val crearProducto: CrearProductoMutation
  
    public val crearUsuario: CrearUsuarioMutation
  
    public val crearVenta: CrearVentaMutation
  
    public val eliminarProducto: EliminarProductoMutation
  
    public val listarClientes: ListarClientesQuery
  
    public val listarInventario: ListarInventarioQuery
  
    public val listarProductos: ListarProductosQuery
  
    public val listarVentas: ListarVentasQuery
  

  public companion object {
    @Suppress("MemberVisibilityCanBePrivate")
    public val config: com.google.firebase.dataconnect.ConnectorConfig = com.google.firebase.dataconnect.ConnectorConfig(
      connector = "example",
      location = "us-east4",
      serviceId = "bams-30596-2-service",
    )

    public fun getInstance(
      dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect
    ):ExampleConnector = synchronized(instances) {
      instances.getOrPut(dataConnect) {
        ExampleConnectorImpl(dataConnect)
      }
    }

    private val instances = java.util.WeakHashMap<com.google.firebase.dataconnect.FirebaseDataConnect, ExampleConnectorImpl>()

    
    public val defaultCacheSettings: com.google.firebase.dataconnect.CacheSettings =
      com.google.firebase.dataconnect.CacheSettings(
        
        
      )

    public val defaultDataConnectSettings: com.google.firebase.dataconnect.DataConnectSettings =
      com.google.firebase.dataconnect.DataConnectSettings(
        cacheSettings = defaultCacheSettings,
      )
    
  }
}

public val ExampleConnector.Companion.instance:ExampleConnector
  get() = getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(
    config, defaultDataConnectSettings
  ))

public fun ExampleConnector.Companion.getInstance(
  settings: com.google.firebase.dataconnect.DataConnectSettings = defaultDataConnectSettings
):ExampleConnector =
  getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(config, settings))

public fun ExampleConnector.Companion.getInstance(
  app: com.google.firebase.FirebaseApp,
  settings: com.google.firebase.dataconnect.DataConnectSettings = defaultDataConnectSettings
):ExampleConnector =
  getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(app, config, settings))

private class ExampleConnectorImpl(
  override val dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect
) : ExampleConnector {
  
    override val actualizarProducto by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ActualizarProductoMutationImpl(this)
    }
  
    override val ajustarInventario by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AjustarInventarioMutationImpl(this)
    }
  
    override val buscarProductoPorCodigo by lazy(LazyThreadSafetyMode.PUBLICATION) {
      BuscarProductoPorCodigoQueryImpl(this)
    }
  
    override val crearCliente by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CrearClienteMutationImpl(this)
    }
  
    override val crearInventario by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CrearInventarioMutationImpl(this)
    }
  
    override val crearProducto by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CrearProductoMutationImpl(this)
    }
  
    override val crearUsuario by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CrearUsuarioMutationImpl(this)
    }
  
    override val crearVenta by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CrearVentaMutationImpl(this)
    }
  
    override val eliminarProducto by lazy(LazyThreadSafetyMode.PUBLICATION) {
      EliminarProductoMutationImpl(this)
    }
  
    override val listarClientes by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListarClientesQueryImpl(this)
    }
  
    override val listarInventario by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListarInventarioQueryImpl(this)
    }
  
    override val listarProductos by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListarProductosQueryImpl(this)
    }
  
    override val listarVentas by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListarVentasQueryImpl(this)
    }
  

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun operations(): List<com.google.firebase.dataconnect.generated.GeneratedOperation<ExampleConnector, *, *>> =
    queries() + mutations()

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun mutations(): List<com.google.firebase.dataconnect.generated.GeneratedMutation<ExampleConnector, *, *>> =
    listOf(
      actualizarProducto,
        ajustarInventario,
        crearCliente,
        crearInventario,
        crearProducto,
        crearUsuario,
        crearVenta,
        eliminarProducto,
        
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun queries(): List<com.google.firebase.dataconnect.generated.GeneratedQuery<ExampleConnector, *, *>> =
    listOf(
      buscarProductoPorCodigo,
        listarClientes,
        listarInventario,
        listarProductos,
        listarVentas,
        
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect) =
    ExampleConnectorImpl(dataConnect)

  override fun equals(other: Any?): Boolean =
    other is ExampleConnectorImpl &&
    other.dataConnect == dataConnect

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "ExampleConnectorImpl",
      dataConnect,
    )

  override fun toString(): String =
    "ExampleConnectorImpl(dataConnect=$dataConnect)"
}



private open class ExampleConnectorGeneratedQueryImpl<Data, Variables>(
  override val connector: ExampleConnector,
  override val operationName: String,
  override val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
  override val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
) : com.google.firebase.dataconnect.generated.GeneratedQuery<ExampleConnector, Data, Variables> {

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(
    connector: ExampleConnector,
    operationName: String,
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
    variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
  ) =
    ExampleConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: kotlinx.serialization.SerializationStrategy<NewVariables>
  ) =
    ExampleConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<NewData>
  ) =
    ExampleConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is ExampleConnectorGeneratedQueryImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "ExampleConnectorGeneratedQueryImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "ExampleConnectorGeneratedQueryImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}

private open class ExampleConnectorGeneratedMutationImpl<Data, Variables>(
  override val connector: ExampleConnector,
  override val operationName: String,
  override val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
  override val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
) : com.google.firebase.dataconnect.generated.GeneratedMutation<ExampleConnector, Data, Variables> {

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(
    connector: ExampleConnector,
    operationName: String,
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
    variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
  ) =
    ExampleConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: kotlinx.serialization.SerializationStrategy<NewVariables>
  ) =
    ExampleConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<NewData>
  ) =
    ExampleConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is ExampleConnectorGeneratedMutationImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "ExampleConnectorGeneratedMutationImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "ExampleConnectorGeneratedMutationImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}



private class ActualizarProductoMutationImpl(
  connector: ExampleConnector
):
  ActualizarProductoMutation,
  ExampleConnectorGeneratedMutationImpl<
      ActualizarProductoMutation.Data,
      ActualizarProductoMutation.Variables
  >(
    connector,
    ActualizarProductoMutation.Companion.operationName,
    ActualizarProductoMutation.Companion.dataDeserializer,
    ActualizarProductoMutation.Companion.variablesSerializer,
  )


private class AjustarInventarioMutationImpl(
  connector: ExampleConnector
):
  AjustarInventarioMutation,
  ExampleConnectorGeneratedMutationImpl<
      AjustarInventarioMutation.Data,
      AjustarInventarioMutation.Variables
  >(
    connector,
    AjustarInventarioMutation.Companion.operationName,
    AjustarInventarioMutation.Companion.dataDeserializer,
    AjustarInventarioMutation.Companion.variablesSerializer,
  )


private class BuscarProductoPorCodigoQueryImpl(
  connector: ExampleConnector
):
  BuscarProductoPorCodigoQuery,
  ExampleConnectorGeneratedQueryImpl<
      BuscarProductoPorCodigoQuery.Data,
      BuscarProductoPorCodigoQuery.Variables
  >(
    connector,
    BuscarProductoPorCodigoQuery.Companion.operationName,
    BuscarProductoPorCodigoQuery.Companion.dataDeserializer,
    BuscarProductoPorCodigoQuery.Companion.variablesSerializer,
  )


private class CrearClienteMutationImpl(
  connector: ExampleConnector
):
  CrearClienteMutation,
  ExampleConnectorGeneratedMutationImpl<
      CrearClienteMutation.Data,
      CrearClienteMutation.Variables
  >(
    connector,
    CrearClienteMutation.Companion.operationName,
    CrearClienteMutation.Companion.dataDeserializer,
    CrearClienteMutation.Companion.variablesSerializer,
  )


private class CrearInventarioMutationImpl(
  connector: ExampleConnector
):
  CrearInventarioMutation,
  ExampleConnectorGeneratedMutationImpl<
      CrearInventarioMutation.Data,
      CrearInventarioMutation.Variables
  >(
    connector,
    CrearInventarioMutation.Companion.operationName,
    CrearInventarioMutation.Companion.dataDeserializer,
    CrearInventarioMutation.Companion.variablesSerializer,
  )


private class CrearProductoMutationImpl(
  connector: ExampleConnector
):
  CrearProductoMutation,
  ExampleConnectorGeneratedMutationImpl<
      CrearProductoMutation.Data,
      CrearProductoMutation.Variables
  >(
    connector,
    CrearProductoMutation.Companion.operationName,
    CrearProductoMutation.Companion.dataDeserializer,
    CrearProductoMutation.Companion.variablesSerializer,
  )


private class CrearUsuarioMutationImpl(
  connector: ExampleConnector
):
  CrearUsuarioMutation,
  ExampleConnectorGeneratedMutationImpl<
      CrearUsuarioMutation.Data,
      CrearUsuarioMutation.Variables
  >(
    connector,
    CrearUsuarioMutation.Companion.operationName,
    CrearUsuarioMutation.Companion.dataDeserializer,
    CrearUsuarioMutation.Companion.variablesSerializer,
  )


private class CrearVentaMutationImpl(
  connector: ExampleConnector
):
  CrearVentaMutation,
  ExampleConnectorGeneratedMutationImpl<
      CrearVentaMutation.Data,
      CrearVentaMutation.Variables
  >(
    connector,
    CrearVentaMutation.Companion.operationName,
    CrearVentaMutation.Companion.dataDeserializer,
    CrearVentaMutation.Companion.variablesSerializer,
  )


private class EliminarProductoMutationImpl(
  connector: ExampleConnector
):
  EliminarProductoMutation,
  ExampleConnectorGeneratedMutationImpl<
      EliminarProductoMutation.Data,
      EliminarProductoMutation.Variables
  >(
    connector,
    EliminarProductoMutation.Companion.operationName,
    EliminarProductoMutation.Companion.dataDeserializer,
    EliminarProductoMutation.Companion.variablesSerializer,
  )


private class ListarClientesQueryImpl(
  connector: ExampleConnector
):
  ListarClientesQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListarClientesQuery.Data,
      Unit
  >(
    connector,
    ListarClientesQuery.Companion.operationName,
    ListarClientesQuery.Companion.dataDeserializer,
    ListarClientesQuery.Companion.variablesSerializer,
  )


private class ListarInventarioQueryImpl(
  connector: ExampleConnector
):
  ListarInventarioQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListarInventarioQuery.Data,
      Unit
  >(
    connector,
    ListarInventarioQuery.Companion.operationName,
    ListarInventarioQuery.Companion.dataDeserializer,
    ListarInventarioQuery.Companion.variablesSerializer,
  )


private class ListarProductosQueryImpl(
  connector: ExampleConnector
):
  ListarProductosQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListarProductosQuery.Data,
      Unit
  >(
    connector,
    ListarProductosQuery.Companion.operationName,
    ListarProductosQuery.Companion.dataDeserializer,
    ListarProductosQuery.Companion.variablesSerializer,
  )


private class ListarVentasQueryImpl(
  connector: ExampleConnector
):
  ListarVentasQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListarVentasQuery.Data,
      Unit
  >(
    connector,
    ListarVentasQuery.Companion.operationName,
    ListarVentasQuery.Companion.dataDeserializer,
    ListarVentasQuery.Companion.variablesSerializer,
  )


