package com.karzek.designsystem.banner

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.karzek.designsystem.token.SpacingToken.X1

@Composable
fun Banner(@StringRes messageRes: Int, variant: BannerVariant) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .configureBackground(variant),
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(X1),
      text = stringResource(id = messageRes),
      style = MaterialTheme.typography.bodyMedium,
      color = getTextColor(variant),
    )
  }
}

@Composable
private fun Modifier.configureBackground(variant: BannerVariant) = when (variant) {
  BannerVariant.INFO -> this.background(MaterialTheme.colorScheme.surface)
  BannerVariant.ERROR -> this.background(MaterialTheme.colorScheme.error)
}

@Composable
private fun getTextColor(variant: BannerVariant) = when (variant) {
  BannerVariant.INFO -> MaterialTheme.colorScheme.onSurface
  BannerVariant.ERROR -> MaterialTheme.colorScheme.onError
}

enum class BannerVariant {
  INFO, ERROR
}