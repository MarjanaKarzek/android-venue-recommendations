package com.karzek.wishlist.data

import androidx.datastore.core.DataStore
import com.karzek.core.coroutines.DispatcherProvider
import com.karzek.core.error.ErrorEntityFactory
import com.karzek.core.result.ResultComplete
import com.karzek.core.result.safeApiCall
import com.karzek.domain.wishlist.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class WishlistRepositoryImpl(
  private val dispatcher: DispatcherProvider,
  private val errorEntityFactory: ErrorEntityFactory,
  private val storage: DataStore<WishlistDto>,
) : WishlistRepository {

  override fun observeRestaurantIds(): Flow<List<String>> = storage.data
    .transform { it.restaurantIdsList }

  override suspend fun putRestaurantId(id: String): ResultComplete {
    return safeApiCall(dispatcher.io, errorEntityFactory) {
      storage.updateData {
        it.apply { restaurantIdsList.add(id) }
      }
    }
  }

  override suspend fun removeRestaurantId(id: String): ResultComplete {
    return safeApiCall(dispatcher.io, errorEntityFactory) {
      storage.updateData {
        it.apply { restaurantIdsList.remove(id) }
      }
    }
  }
}