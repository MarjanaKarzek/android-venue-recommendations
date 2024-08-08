package com.karzek.wishlist.data

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import java.io.InputStream
import java.io.OutputStream

private const val WISHLIST_DATA_STORE_FILE_NAME ="wishlist.pb"

object WishlistSerializer : Serializer<WishlistData> {
  override val defaultValue: WishlistData = WishlistData.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): WishlistData {
    return WishlistData.parseFrom(input)
  }

  override suspend fun writeTo(t: WishlistData, output: OutputStream) {
    t.writeTo(output)
  }
}

val Context.wishlistDataStore by dataStore(
  fileName = WISHLIST_DATA_STORE_FILE_NAME,
  serializer = WishlistSerializer
)
