package com.karzek.domain.wishlist

import com.karzek.core.result.ResultComplete
import kotlinx.coroutines.flow.StateFlow

interface WishlistRepository {
  fun observeRestaurantIds(): StateFlow<List<String>>
  suspend fun putRestaurantId(id: String): ResultComplete
  suspend fun removeRestaurantId(id: String): ResultComplete
}