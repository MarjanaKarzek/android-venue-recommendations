package com.karzek.venues.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karzek.core.error.ErrorEntity
import com.karzek.core.network.NetworkConnectionError
import com.karzek.core.result.Result
import com.karzek.designsystem.R
import com.karzek.domain.restaurants.GetRestaurantsUseCase
import com.karzek.domain.restaurants.NoRestaurantsFoundError
import com.karzek.domain.restaurants.Restaurant
import com.karzek.domain.restaurants.RestaurantOutput
import com.karzek.domain.wishlist.WishlistRepository
import com.karzek.location.work.LocationNotificationWorkScheduler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
  private val useCase: GetRestaurantsUseCase,
  private val wishlistRepository: WishlistRepository,
  private val mainViewProvider: MainViewProvider,
  private val workScheduler: LocationNotificationWorkScheduler,
) : ViewModel() {

  private val _viewState: MutableStateFlow<MainViewState> = MutableStateFlow(MainViewState())
  val viewState: StateFlow<MainViewState> = _viewState

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
    _viewState.value = MainViewState(
      isLoading = false,
      data = mainViewProvider.getViewItems(
        data = data,
        onWishIconClicked = ::onWishIconClicked,
      ),
      error = null,
    )
  }

  private fun onWishIconClicked(restaurant: Restaurant, isWishListed: Boolean) {
    viewModelScope.launch {
      if (isWishListed) {
        wishlistRepository.removeRestaurantId(restaurant.id)
      } else {
        wishlistRepository.putRestaurantId(restaurant.id)
      }
    }
  }

  private fun showError(error: ErrorEntity) {
    Timber.e("DATA ERROR", "${error.throwable.message}")
    val resource = when (error) {
      is NoRestaurantsFoundError -> R.string.venues_no_restaurants_found_error
      is NetworkConnectionError -> R.string.app_no_network_error
      else -> R.string.app_unknown_error
    }
    _viewState.value = _viewState.value.copy(isLoading = false, error = resource)
  }

  fun onPause() {
    workScheduler.scheduleLocationNotification()
  }

  fun onResume() {
    workScheduler.cancelAllWork()
  }

}