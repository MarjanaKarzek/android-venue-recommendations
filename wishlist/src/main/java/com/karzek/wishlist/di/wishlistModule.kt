package com.karzek.wishlist.di

import com.karzek.domain.wishlist.WishlistRepository
import com.karzek.wishlist.data.WishlistRepositoryImpl
import org.koin.dsl.module

val wishlistModule = module {
  single<WishlistRepository> {
    WishlistRepositoryImpl(
      dataStore = get(),
      moshi = get(),
      dispatcher = get(),
      errorEntityFactory = get(),
    )
  }

}
