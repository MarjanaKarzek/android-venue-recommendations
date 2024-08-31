package com.karzek.wishlist.data

import android.content.SharedPreferences
import android.util.Log
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
  private val storage: SharedPreferences,
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
      var currentRestaurantIds = currentWishlist.restaurantIds.toMutableList()
      currentRestaurantIds = currentRestaurantIds.apply { add(id) }
      putWishlist(Wishlist(currentRestaurantIds))
      _wishlistIds.value = currentWishlist.restaurantIds
    }
  }

  override suspend fun removeRestaurantId(id: String): ResultComplete {
    return safeApiCall(dispatcher.io, errorEntityFactory) {
      val currentWishlist = getWishlist()
      currentWishlist.restaurantIds.toMutableList().remove(id)
      putWishlist(currentWishlist)
      _wishlistIds.value = currentWishlist.restaurantIds
    }
  }

  private fun getWishlist(): Wishlist {
    val json = storage.getString(KEY_WISH_LISTED_RESTAURANT_IDS, null)
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
      storage.edit().putString(KEY_WISH_LISTED_RESTAURANT_IDS, json).commit()
    }
  }
}