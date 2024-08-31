package com.karzek.domain.restaurants

import com.karzek.core.coroutines.SuspendOutUseCase
import com.karzek.core.result.Result
import com.karzek.domain.location.Location
import com.karzek.domain.location.LocationRepository
import com.karzek.domain.wishlist.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

private const val LIMIT_RESTAURANTS: Int = 15

class GetRestaurantsUseCase(
  private val locationRepository: LocationRepository,
  private val wishlistRepository: WishlistRepository,
  private val restaurantRepository: RestaurantRepository,
) : SuspendOutUseCase<Flow<Result<RestaurantOutput>>> {

  private var lastLocation: Location? = null
  private var lastRestaurants: List<Restaurant> = emptyList()

  override suspend fun execute(): Flow<Result<RestaurantOutput>> {
    return combine(
      locationRepository.observeUserLocation(),
      wishlistRepository.observeRestaurantIds(),
    ) { currentLocation, restaurantIds ->
      if (lastRestaurants.isEmpty() || hasLocationChanged(currentLocation)) {
        lastLocation = currentLocation
        when (val result = restaurantRepository.getRestaurants(
          currentLocation.latitude,
          currentLocation.longitude,
          LIMIT_RESTAURANTS
        )) {
          is Result.Success -> {
            lastRestaurants = result.data
            Result.Success(RestaurantOutput(lastRestaurants, restaurantIds))
          }

          is Result.Error -> result
        }
      } else {
        Result.Success(RestaurantOutput(lastRestaurants, restaurantIds))
      }
    }
  }

  private fun hasLocationChanged(currentLocation: Location): Boolean {
    return lastLocation != currentLocation
  }
}

data class RestaurantOutput(
  val restaurants: List<Restaurant>,
  val restaurantIds: List<String>,
)