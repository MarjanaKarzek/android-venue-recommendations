package com.karzek.restaurants.data

import com.karzek.core.coroutines.DispatcherProvider
import kotlinx.coroutines.Dispatchers

class TestDispatcherProvider : DispatcherProvider {
  override val default = Dispatchers.Unconfined
  override val io = Dispatchers.Unconfined
  override val main = Dispatchers.Unconfined
}