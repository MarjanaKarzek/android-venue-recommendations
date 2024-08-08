package com.karzek.wishlist.data

import android.content.SharedPreferences
import com.karzek.core.coroutines.DispatcherProvider
import com.karzek.core.error.ErrorEntityFactory
import com.karzek.core.result.ResultComplete
import com.karzek.core.result.safeApiCall
import com.karzek.domain.wishlist.WishlistRepository
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class WishlistRepositoryImpl(
  private val dataStore: SharedPreferences,
  private val moshi: Moshi,
  private val dispatcher: DispatcherProvider,
  private val errorEntityFactory: ErrorEntityFactory,
) : WishlistRepository {

  companion object {
    private const val KEY_WISH_LISTED_RESTAURANT_IDS = "KEY_WISH_LISTED_RESTAURANT_IDS"
  }

  private val _wishlistIds: MutableStateFlow<List<String>> =
    MutableStateFlow(getWishlist().restaurantIds)

  override fun observeRestaurantIds(): StateFlow<List<String>> = _wishlistIds

  override suspend fun putRestaurantId(id: String): ResultComplete {
    return safeApiCall(dispatcher.io, errorEntityFactory) {
      val currentWishlist = getWishlist()
      currentWishlist.restaurantIds.toMutableList().add(id)
      putWishlist(currentWishlist)
    }
  }

  override suspend fun removeRestaurantId(id: String): ResultComplete {
    return safeApiCall(dispatcher.io, errorEntityFactory) {
      val currentWishlist = getWishlist()
      currentWishlist.restaurantIds.toMutableList().remove(id)
      putWishlist(currentWishlist)
    }
  }

  private fun getWishlist(): Wishlist {
    val json = dataStore.getString(KEY_WISH_LISTED_RESTAURANT_IDS, null)
    return if (json == null) {
      Wishlist(emptyList())
    } else {
      try {
        moshi.adapter(Wishlist::class.java).fromJson(json) ?: Wishlist(emptyList())
      } catch (e: Exception) {
        // state can't be recovered but should be logged for observation
        Wishlist(emptyList())
      }
    }
  }

  private suspend fun putWishlist(wishlist: Wishlist) {
    withContext(dispatcher.io) {
      val json = moshi.adapter(Wishlist::class.java).toJson(wishlist)
      dataStore.edit().putString(KEY_WISH_LISTED_RESTAURANT_IDS, json).commit()
    }
  }
}