package com.karzek.venues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karzek.core.error.ErrorEntity
import com.karzek.core.result.Result
import com.karzek.designsystem.venue.VenueCardData
import com.karzek.domain.location.LocationRepository
import com.karzek.domain.restaurants.Restaurant
import com.karzek.domain.restaurants.RestaurantRepository
import com.karzek.domain.wishlist.WishlistRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.UUID

class MainViewModel(
  private val locationRepository: LocationRepository,
  private val restaurantRepository: RestaurantRepository,
  private val wishlistRepository: WishlistRepository,
) : ViewModel() {

  companion object {
    private const val LIMIT_VENUES: Int = 15
  }

  private val _viewState: MutableStateFlow<List<VenueCardData>> = MutableStateFlow(emptyList())
  val viewState: StateFlow<List<VenueCardData>> = _viewState


  private suspend fun fetchRestaurants(latitude: String, longitude: String) {
    when (val result = restaurantRepository.getRestaurants(latitude, longitude, LIMIT_VENUES)) {
      is Result.Success -> showRestaurants(result.data)
      is Result.Error -> showError(result.error)
    }
  }

  fun handleWishlist() {
    viewModelScope.launch {
      wishlistRepository.observeRestaurantIds().collect {
        Timber.d("WISHLIST DATA", "$it")
      }
    }
    viewModelScope.launch {
      val newID = UUID.randomUUID()
      Timber.d("WISHLIST DATA", "new id: $newID")
      wishlistRepository.putRestaurantId(newID.toString())
    }
  }

  fun handleLocation() {
    viewModelScope.launch {
      locationRepository.observeUserLocation().collect {
        Timber.d("LOCATION DATA", "${it.latitude}, ${it.longitude}")
        fetchRestaurants(it.latitude, it.longitude)
      }
    }
  }

  private fun showRestaurants(data: List<Restaurant>) {
    Timber.d("DATA SUCCESS", "${data.map { "${it.name} \n" }}")
    _viewState.value = data.map {
      VenueCardData(
        name = it.name,
        shortDescription = it.shortDescription,
        imageUrl = it.imageUrl,
        isWishListed = true,
      )
    }
  }

  private fun showError(error: ErrorEntity) {
    Timber.e("DATA ERROR", "${error.throwable.message}")
  }

}