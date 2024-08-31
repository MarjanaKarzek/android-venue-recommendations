package com.karzek.wishlist.di

import android.content.Context
import android.content.SharedPreferences
import com.karzek.domain.wishlist.WishlistRepository
import com.karzek.wishlist.data.WishlistRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val WISHLIST_STORAGE_IDENTIFIER = "WISHLIST_STORAGE_IDENTIFIER"

val wishlistDataModule = module {
  single<WishlistRepository> {
    WishlistRepositoryImpl(
      storage = get(),
      moshi = get(),
      dispatcher = get(),
      errorEntityFactory = get(),
    )
  }

  single<SharedPreferences> {
    androidContext().getSharedPreferences(
      WISHLIST_STORAGE_IDENTIFIER,
      Context.MODE_PRIVATE
    )
  }

}
