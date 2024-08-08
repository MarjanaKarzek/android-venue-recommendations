package com.karzek.wishlist.data

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.karzek.core.coroutines.DispatcherProvider
import com.karzek.core.error.ErrorEntityFactory
import com.karzek.core.result.ResultComplete
import com.karzek.core.result.safeApiCall
import com.karzek.domain.wishlist.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform

class WishlistRepositoryImpl(
  private val storage: DataStore<WishlistData>,
  private val dispatcher: DispatcherProvider,
  private val errorEntityFactory: ErrorEntityFactory,
) : WishlistRepository {

  override fun observeRestaurantIds(): Flow<List<String>> = storage.data
    .catch { exception ->
      if (exception is IOException) {
        emit(WishlistData.getDefaultInstance())
      } else {
        throw exception
      }
    }
    .transform {
      it.restaurantIdsList ?: emptyList()
    }

  override suspend fun putRestaurantId(id: String): ResultComplete {
    return safeApiCall(dispatcher.io, errorEntityFactory) {
      storage.updateData { data ->
        data.toBuilder()
          .addRestaurantIds(id)
          .build()
      }
    }
  }

  override suspend fun removeRestaurantId(id: String): ResultComplete {
    return safeApiCall(dispatcher.io, errorEntityFactory) {
      storage.updateData { data ->
        val updatedRestaurantIds = data.restaurantIdsList.filterNot { it == id }

        data.toBuilder()
          .clearRestaurantIds()
          .addAllRestaurantIds(updatedRestaurantIds)
          .build()
      }
    }
  }
}