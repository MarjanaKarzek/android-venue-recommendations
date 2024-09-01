package com.karzek.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.karzek.designsystem.token.backgroundDark
import com.karzek.designsystem.token.backgroundLight
import com.karzek.designsystem.token.errorContainerDark
import com.karzek.designsystem.token.errorContainerLight
import com.karzek.designsystem.token.errorDark
import com.karzek.designsystem.token.errorLight
import com.karzek.designsystem.token.inverseOnSurfaceDark
import com.karzek.designsystem.token.inverseOnSurfaceLight
import com.karzek.designsystem.token.inversePrimaryDark
import com.karzek.designsystem.token.inversePrimaryLight
import com.karzek.designsystem.token.inverseSurfaceDark
import com.karzek.designsystem.token.inverseSurfaceLight
import com.karzek.designsystem.token.onBackgroundDark
import com.karzek.designsystem.token.onBackgroundLight
import com.karzek.designsystem.token.onErrorContainerDark
import com.karzek.designsystem.token.onErrorContainerLight
import com.karzek.designsystem.token.onErrorDark
import com.karzek.designsystem.token.onErrorLight
import com.karzek.designsystem.token.onPrimaryContainerDark
import com.karzek.designsystem.token.onPrimaryContainerLight
import com.karzek.designsystem.token.onPrimaryDark
import com.karzek.designsystem.token.onPrimaryLight
import com.karzek.designsystem.token.onSecondaryContainerDark
import com.karzek.designsystem.token.onSecondaryContainerLight
import com.karzek.designsystem.token.onSecondaryDark
import com.karzek.designsystem.token.onSecondaryLight
import com.karzek.designsystem.token.onSurfaceDark
import com.karzek.designsystem.token.onSurfaceLight
import com.karzek.designsystem.token.onSurfaceVariantDark
import com.karzek.designsystem.token.onSurfaceVariantLight
import com.karzek.designsystem.token.onTertiaryContainerDark
import com.karzek.designsystem.token.onTertiaryContainerLight
import com.karzek.designsystem.token.onTertiaryDark
import com.karzek.designsystem.token.onTertiaryLight
import com.karzek.designsystem.token.outlineDark
import com.karzek.designsystem.token.outlineLight
import com.karzek.designsystem.token.outlineVariantDark
import com.karzek.designsystem.token.outlineVariantLight
import com.karzek.designsystem.token.primaryContainerDark
import com.karzek.designsystem.token.primaryContainerLight
import com.karzek.designsystem.token.primaryDark
import com.karzek.designsystem.token.primaryLight
import com.karzek.designsystem.token.scrimDark
import com.karzek.designsystem.token.scrimLight
import com.karzek.designsystem.token.secondaryContainerDark
import com.karzek.designsystem.token.secondaryContainerLight
import com.karzek.designsystem.token.secondaryDark
import com.karzek.designsystem.token.secondaryLight
import com.karzek.designsystem.token.surfaceBrightDark
import com.karzek.designsystem.token.surfaceBrightLight
import com.karzek.designsystem.token.surfaceContainerDark
import com.karzek.designsystem.token.surfaceContainerHighDark
import com.karzek.designsystem.token.surfaceContainerHighLight
import com.karzek.designsystem.token.surfaceContainerHighestDark
import com.karzek.designsystem.token.surfaceContainerHighestLight
import com.karzek.designsystem.token.surfaceContainerLight
import com.karzek.designsystem.token.surfaceContainerLowDark
import com.karzek.designsystem.token.surfaceContainerLowLight
import com.karzek.designsystem.token.surfaceContainerLowestDark
import com.karzek.designsystem.token.surfaceContainerLowestLight
import com.karzek.designsystem.token.surfaceDark
import com.karzek.designsystem.token.surfaceDimDark
import com.karzek.designsystem.token.surfaceDimLight
import com.karzek.designsystem.token.surfaceLight
import com.karzek.designsystem.token.surfaceVariantDark
import com.karzek.designsystem.token.surfaceVariantLight
import com.karzek.designsystem.token.tertiaryContainerDark
import com.karzek.designsystem.token.tertiaryContainerLight
import com.karzek.designsystem.token.tertiaryDark
import com.karzek.designsystem.token.tertiaryLight

internal val lightScheme = lightColorScheme(
  primary = primaryLight,
  onPrimary = onPrimaryLight,
  primaryContainer = primaryContainerLight,
  onPrimaryContainer = onPrimaryContainerLight,
  secondary = secondaryLight,
  onSecondary = onSecondaryLight,
  secondaryContainer = secondaryContainerLight,
  onSecondaryContainer = onSecondaryContainerLight,
  tertiary = tertiaryLight,
  onTertiary = onTertiaryLight,
  tertiaryContainer = tertiaryContainerLight,
  onTertiaryContainer = onTertiaryContainerLight,
  error = errorLight,
  onError = onErrorLight,
  errorContainer = errorContainerLight,
  onErrorContainer = onErrorContainerLight,
  background = backgroundLight,
  onBackground = onBackgroundLight,
  surface = surfaceLight,
  onSurface = onSurfaceLight,
  surfaceVariant = surfaceVariantLight,
  onSurfaceVariant = onSurfaceVariantLight,
  outline = outlineLight,
  outlineVariant = outlineVariantLight,
  scrim = scrimLight,
  inverseSurface = inverseSurfaceLight,
  inverseOnSurface = inverseOnSurfaceLight,
  inversePrimary = inversePrimaryLight,
  surfaceDim = surfaceDimLight,
  surfaceBright = surfaceBrightLight,
  surfaceContainerLowest = surfaceContainerLowestLight,
  surfaceContainerLow = surfaceContainerLowLight,
  surfaceContainer = surfaceContainerLight,
  surfaceContainerHigh = surfaceContainerHighLight,
  surfaceContainerHighest = surfaceContainerHighestLight,
)

internal val darkScheme = darkColorScheme(
  primary = primaryDark,
  onPrimary = onPrimaryDark,
  primaryContainer = primaryContainerDark,
  onPrimaryContainer = onPrimaryContainerDark,
  secondary = secondaryDark,
  onSecondary = onSecondaryDark,
  secondaryContainer = secondaryContainerDark,
  onSecondaryContainer = onSecondaryContainerDark,
  tertiary = tertiaryDark,
  onTertiary = onTertiaryDark,
  tertiaryContainer = tertiaryContainerDark,
  onTertiaryContainer = onTertiaryContainerDark,
  error = errorDark,
  onError = onErrorDark,
  errorContainer = errorContainerDark,
  onErrorContainer = onErrorContainerDark,
  background = backgroundDark,
  onBackground = onBackgroundDark,
  surface = surfaceDark,
  onSurface = onSurfaceDark,
  surfaceVariant = surfaceVariantDark,
  onSurfaceVariant = onSurfaceVariantDark,
  outline = outlineDark,
  outlineVariant = outlineVariantDark,
  scrim = scrimDark,
  inverseSurface = inverseSurfaceDark,
  inverseOnSurface = inverseOnSurfaceDark,
  inversePrimary = inversePrimaryDark,
  surfaceDim = surfaceDimDark,
  surfaceBright = surfaceBrightDark,
  surfaceContainerLowest = surfaceContainerLowestDark,
  surfaceContainerLow = surfaceContainerLowDark,
  surfaceContainer = surfaceContainerDark,
  surfaceContainerHigh = surfaceContainerHighDark,
  surfaceContainerHighest = surfaceContainerHighestDark,
)