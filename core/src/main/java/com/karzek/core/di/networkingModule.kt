package com.karzek.core.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://restaurant-api.wolt.com/"

val networkingModule = module {
  single { OkHttpClient.Builder() }

  single<Retrofit> {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(get()))
      .build()
  }
}