package com.karzek.wishlist.di

import androidx.datastore.core.DataStore
import com.karzek.domain.wishlist.WishlistRepository
import com.karzek.wishlist.data.WishlistData
import com.karzek.wishlist.data.WishlistRepositoryImpl
import com.karzek.wishlist.data.wishlistDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val wishlistDataModule = module {
  single<WishlistRepository> {
    WishlistRepositoryImpl(
      storage = get(),
      dispatcher = get(),
      errorEntityFactory = get(),
    )
  }

  single<DataStore<WishlistData>> { androidContext().wishlistDataStore }

}
