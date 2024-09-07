package com.karzek.core.di

import com.karzek.core.network.NetworkConfiguration
import com.karzek.core.network.NetworkConfigurationImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkingModule = module {
  single { OkHttpClient.Builder() }

  single<Retrofit> {
    val networkConfiguration: NetworkConfiguration = get()
    Retrofit.Builder()
      .baseUrl(networkConfiguration.baseUrl)
      .addConverterFactory(MoshiConverterFactory.create(get()))
      .build()
  }

  single<NetworkConfiguration> { NetworkConfigurationImpl() }
}