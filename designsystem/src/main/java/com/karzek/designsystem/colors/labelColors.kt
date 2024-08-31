package com.karzek.designsystem.colors

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorScheme.labelPrimary: Color
  @Composable
  get() = if (!isSystemInDarkTheme()) Palette.BLUE_5 else Color.White
