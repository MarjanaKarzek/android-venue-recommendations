package com.karzek.core.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkingModule = module {
  single { OkHttpClient.Builder().addInterceptor(ChuckerInterceptor(get())) }

  single { Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(get())) }
}