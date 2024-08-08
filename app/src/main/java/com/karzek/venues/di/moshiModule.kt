package com.karzek.venues.di

import com.karzek.restaurants.data.api.sectionJsonParser
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

val moshiModule = module {
  single {
    Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(sectionJsonParser).build()
  }
}