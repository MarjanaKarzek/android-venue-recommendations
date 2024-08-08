package com.karzek.venues

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karzek.core.error.ErrorEntity
import com.karzek.core.result.Result
import com.karzek.domain.restaurants.Restaurant
import com.karzek.domain.restaurants.RestaurantRepository
import kotlinx.coroutines.launch

class MainViewModel(private val restaurantRepository: RestaurantRepository) : ViewModel() {

  fun fetchRestaurants() {
    viewModelScope.launch {
      when (val result = restaurantRepository.getRestaurants("60.170187", "24.930599", 15)) {
        is Result.Success -> showRestaurants(result.data)
        is Result.Error -> showError(result.error)
      }
    }
  }

  private fun showRestaurants(data: List<Restaurant>) {
    Log.d("DATA SUCCESS", "${data.map { "${it.name} \n" }}")
  }

  private fun showError(error: ErrorEntity) {
    Log.e("DATA ERROR", "${error.throwable.message}")
  }

}