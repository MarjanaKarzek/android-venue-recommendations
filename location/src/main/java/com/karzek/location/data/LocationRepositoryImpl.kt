package com.karzek.location.data

import com.karzek.core.coroutines.DispatcherProvider
import com.karzek.domain.location.Location
import com.karzek.domain.location.LocationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocationRepositoryImpl(
  private val dispatcherProvider: DispatcherProvider,
) : LocationRepository {

  companion object {
    private const val DELAY_IN_MS: Long = 10_000L
  }

  private val locations: List<Location> = buildList {
    add(Location(latitude = "60.170187", longitude = "24.930599"))
    add(Location(latitude = "60.169418", longitude = "24.931618"))
    add(Location(latitude = "60.169818", longitude = "24.932906"))
    add(Location(latitude = "60.170005", longitude = "24.935105"))
    add(Location(latitude = "60.169108", longitude = "24.936210"))
    add(Location(latitude = "60.168355", longitude = "24.934869"))
    add(Location(latitude = "60.167560", longitude = "24.932562"))
    add(Location(latitude = "60.168254", longitude = "24.931532"))
    add(Location(latitude = "60.169012", longitude = "24.930341"))
    add(Location(latitude = "60.170085", longitude = "24.929569"))
  }

  private var pointer: Int = 0

  override fun observeUserLocation(): Flow<Location> = flow {
    while (true) {
      pointer += 1
      emit(locations[pointer % locations.size])
      delay(DELAY_IN_MS)
    }
  }.flowOn(dispatcherProvider.io)

}