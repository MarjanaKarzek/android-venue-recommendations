package com.karzek.domain.restaurants

import com.karzek.core.coroutines.SuspendOutUseCase
import com.karzek.core.result.Result
import com.karzek.domain.location.Location
import com.karzek.domain.location.LocationRepository
import com.karzek.domain.wishlist.WishlistRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

private const val LIMIT_RESTAURANTS: Int = 15

class GetRestaurantsUseCase(
  private val locationRepository: LocationRepository,
  private val wishlistRepository: WishlistRepository,
  private val restaurantRepository: RestaurantRepository
) : SuspendOutUseCase<Flow<Result<RestaurantOutput>>> {

  @OptIn(ExperimentalCoroutinesApi::class)
  override suspend fun execute(): Flow<Result<RestaurantOutput>> {
    return observeUserLocationChanges()
      .flatMapLatest { currentLocation ->
        fetchRestaurantsForLocation(currentLocation)
      }
      .combineWithWishlist()
  }

  private fun observeUserLocationChanges(): Flow<Location> {
    return locationRepository.observeUserLocation()
      .distinctUntilChanged { oldLocation, newLocation ->
        oldLocation == newLocation
      }
  }

  private fun fetchRestaurantsForLocation(location: Location): Flow<Result<List<Restaurant>>> {
    return flow {
      val result = restaurantRepository.getRestaurants(
        location.latitude,
        location.longitude,
        LIMIT_RESTAURANTS
      )
      emit(result)
    }
  }

  private fun Flow<Result<List<Restaurant>>>.combineWithWishlist(): Flow<Result<RestaurantOutput>> {
    return combine(wishlistRepository.observeRestaurantIds()) { restaurantsResult, wishlistIds ->
      when (restaurantsResult) {
        is Result.Success -> Result.Success(RestaurantOutput(restaurantsResult.data, wishlistIds))
        is Result.Error -> restaurantsResult
      }
    }
  }
}

data class RestaurantOutput(
  val restaurants: List<Restaurant>,
  val restaurantIds: List<String>,
)