package com.karzek.core.di

import com.karzek.core.coroutines.DispatcherProvider
import com.karzek.core.coroutines.DispatcherProviderImpl
import org.koin.dsl.module

val coreModule = module {
  single<DispatcherProvider> { DispatcherProviderImpl() }
}