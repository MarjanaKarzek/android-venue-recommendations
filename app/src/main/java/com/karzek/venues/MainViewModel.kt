package com.karzek.venues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karzek.core.error.ErrorEntity
import com.karzek.core.result.Result
import com.karzek.designsystem.venue.VenueCardData
import com.karzek.domain.restaurants.GetRestaurantsUseCase
import com.karzek.domain.restaurants.RestaurantOutput
import com.karzek.domain.wishlist.WishlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
  private val useCase: GetRestaurantsUseCase,
  private val wishlistRepository: WishlistRepository,
) : ViewModel() {

  private val _viewState: MutableStateFlow<List<VenueCardData>> = MutableStateFlow(emptyList())
  val viewState: StateFlow<List<VenueCardData>> = _viewState

  init {
    viewModelScope.launch {
      fetchRestaurants()
    }
  }

  private suspend fun fetchRestaurants() {
    useCase.execute().collectLatest { result ->
      when (result) {
        is Result.Success -> showRestaurants(result.data)
        is Result.Error -> showError(result.error)
      }
    }

  }

  private fun showRestaurants(data: RestaurantOutput) {
    Timber.d("DATA SUCCESS", "${data.restaurants.map { "${it.name} \n" }}")
    _viewState.value = data.restaurants.map {
      VenueCardData(
        name = it.name,
        shortDescription = it.shortDescription,
        imageUrl = it.imageUrl,
        isWishListed = data.restaurantIds.contains(it.id),
        onWishClick = {
          viewModelScope.launch {
            if(data.restaurantIds.contains(it.id)) {
              wishlistRepository.removeRestaurantId(it.id)
            } else {
              wishlistRepository.putRestaurantId(it.id)
            }
          }
        }
      )
    }
  }

  private fun showError(error: ErrorEntity) {
    Timber.e("DATA ERROR", "${error.throwable.message}")
  }

}