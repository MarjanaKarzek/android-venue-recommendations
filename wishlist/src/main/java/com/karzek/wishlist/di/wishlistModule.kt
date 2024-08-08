package com.karzek.wishlist.di

import androidx.datastore.core.DataStore
import com.karzek.domain.wishlist.WishlistRepository
import com.karzek.wishlist.data.WishlistDto
import com.karzek.wishlist.data.WishlistRepositoryImpl
import com.karzek.wishlist.data.wishlistDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val wishlistModule = module {
  single<WishlistRepository> {
    WishlistRepositoryImpl(
      dispatcher = get(),
      errorEntityFactory = get(),
      storage = get(),
    )
  }

  single<DataStore<WishlistDto>> { androidContext().wishlistDataStore }

}
