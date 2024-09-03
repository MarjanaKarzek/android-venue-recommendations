package com.karzek.designsystem.banner

import androidx.annotation.StringRes
import androidx.compose.foundation.background
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
  when (variant) {
    BannerVariant.INFO -> BannerInfo(messageRes)
    BannerVariant.ERROR -> BannerError(messageRes)
  }
}

@Composable
fun BannerInfo(@StringRes messageRes: Int) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(X1)
      .background(MaterialTheme.colorScheme.surface),
    text = stringResource(id = messageRes),
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.onSurface,
  )
}

@Composable
fun BannerError(@StringRes messageRes: Int) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.error)
      .padding(X1),
    text = stringResource(id = messageRes),
    style = MaterialTheme.typography.bodyMedium,
    color = MaterialTheme.colorScheme.onError,
  )
}


enum class BannerVariant {
  INFO, ERROR
}