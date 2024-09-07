package com.karzek.domain.location

import kotlinx.coroutines.flow.Flow

interface LocationRepository {
  fun observeUserLocation(): Flow<Location>
}