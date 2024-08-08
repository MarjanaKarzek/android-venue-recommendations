package com.karzek.wishlist.data

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import java.io.InputStream
import java.io.OutputStream
/*
object WishlistSerializer : Serializer<Wishlist> {
  override val defaultValue: Wishlist = Wishlist.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): Wishlist {
    return Wishlist.parseFrom(input)
  }

  override suspend fun writeTo(wishlist: Wishlist, output: OutputStream) {
    wishlist.writeTo(output)
  }
}

val Context.wishlistDataStore by dataStore(
  fileName = "wishlist.pb",
  serializer = WishlistSerializer
)

 */