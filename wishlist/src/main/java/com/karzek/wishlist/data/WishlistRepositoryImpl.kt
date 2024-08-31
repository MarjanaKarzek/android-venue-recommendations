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
import timber.log.Timber

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
      val currentRestaurantIds = getWishlist().restaurantIds.toMutableList().apply { add(id) }
      putWishlist(WishlistDto(currentRestaurantIds))
      _wishlistIds.value = currentRestaurantIds
    }
  }

  override suspend fun removeRestaurantId(id: String): ResultComplete {
    return safeApiCall(dispatcher.io, errorEntityFactory) {
      val currentRestaurantIds = getWishlist().restaurantIds.toMutableList().apply { remove(id) }
      putWishlist(WishlistDto(currentRestaurantIds))
      _wishlistIds.value = currentRestaurantIds
    }
  }

  private fun getWishlist(): WishlistDto {
    val json = storage.getString(KEY_WISH_LISTED_RESTAURANT_IDS, null)
    return if (json == null) {
      WishlistDto(emptyList())
    } else {
      try {
        moshi.adapter(WishlistDto::class.java).fromJson(json) ?: WishlistDto(emptyList())
      } catch (e: Exception) {
        // state can't be recovered
        Timber.e(e)
        storage.edit().remove(KEY_WISH_LISTED_RESTAURANT_IDS).apply()
        WishlistDto(emptyList())
      }
    }
  }

  private fun putWishlist(wishlist: WishlistDto) {
    val json = moshi.adapter(WishlistDto::class.java).toJson(wishlist)
    storage.edit().putString(KEY_WISH_LISTED_RESTAURANT_IDS, json).commit()
  }
}