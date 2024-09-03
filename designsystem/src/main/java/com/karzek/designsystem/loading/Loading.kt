package com.karzek.designsystem.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loading(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier.background(MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f)),
    contentAlignment = Alignment.Center,
  ) {
    CircularProgressIndicator(
      modifier = Modifier.width(64.dp),
      color = MaterialTheme.colorScheme.primary,
      trackColor = MaterialTheme.colorScheme.inverseSurface,
    )
  }
}
