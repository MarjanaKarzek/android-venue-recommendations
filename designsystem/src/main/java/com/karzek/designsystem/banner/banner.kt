package com.karzek.designsystem.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.karzek.designsystem.token.SpacingToken.X1

@Composable
fun Banner(message: String, variant: BannerVariant) {
  when (variant) {
    BannerVariant.INFO -> BannerInfo(message)
    BannerVariant.ERROR -> BannerError(message)
  }
}

@Composable
fun BannerInfo(message: String) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(X1)
      .background(MaterialTheme.colorScheme.surface),
    text = message,
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.onSurface,
  )
}

@Composable
fun BannerError(message: String) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.error)
      .padding(X1),
    text = message,
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.onError,
  )
}


enum class BannerVariant {
  INFO, ERROR
}