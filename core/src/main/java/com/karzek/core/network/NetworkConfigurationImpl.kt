package com.karzek.core.network

class NetworkConfigurationImpl : NetworkConfiguration {
  override val baseUrl: String
    get() = "https://restaurant-api.wolt.com/"
}