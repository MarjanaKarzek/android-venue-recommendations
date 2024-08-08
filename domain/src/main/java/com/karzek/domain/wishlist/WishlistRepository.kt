package com.karzek.domain.wishlist

import com.karzek.core.result.ResultComplete
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
  fun observeRestaurantIds(): Flow<List<String>>
  suspend fun putRestaurantId(id: String): ResultComplete
  suspend fun removeRestaurantId(id: String): ResultComplete
}