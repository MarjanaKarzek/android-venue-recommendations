package com.karzek.venues.di

import com.karzek.core.error.ComposedErrorEntityFactory
import com.karzek.core.error.ErrorEntityFactory
import com.karzek.core.network.NetworkErrorEntityFactory
import com.karzek.restaurants.data.api.sectionJsonParser
import com.karzek.venues.MainViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseModule = module {
  single {
    Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(sectionJsonParser).build()
  }
  single<ErrorEntityFactory> {
    ComposedErrorEntityFactory(
      listOf(NetworkErrorEntityFactory)
    )
  }
  viewModel { MainViewModel(useCase = get(), wishlistRepository = get()) }
}