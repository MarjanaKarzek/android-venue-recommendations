package com.karzek.designsystem.decoration

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.surfaceGradient(): Modifier {
  return this.background(
    brush = Brush.verticalGradient(
      colorStops = arrayOf(
        0.0f to Color.Transparent,
        0.2f to MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
        0.55f to MaterialTheme.colorScheme.surface,
        1f to MaterialTheme.colorScheme.surface,
      )
    )
  )
}