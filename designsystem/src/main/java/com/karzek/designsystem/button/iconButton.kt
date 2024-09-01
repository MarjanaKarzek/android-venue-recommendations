package com.karzek.designsystem.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
  @DrawableRes icon: Int,
  contentDescription: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
  val isPressedState = interactionSource.collectIsPressedAsState()
  Box(
    modifier = modifier
      .size(44.dp)
      .configureClick(enabled, interactionSource, onClick)
      .configureBackground(enabled, isPressedState.value)
  ) {
    Icon(
      modifier = Modifier.align(Alignment.Center),
      painter = painterResource(id = icon),
      contentDescription = contentDescription,
      tint = getIconTint(enabled, isPressedState.value),
    )
  }
}

@Composable
private fun Modifier.configureClick(
  enabled: Boolean,
  interactionSource: MutableInteractionSource,
  onClick: () -> Unit
) = if (enabled) {
  clickable(
    interactionSource = interactionSource,
    indication = null,
    onClick = onClick,
  )
} else {
  this
}

@Composable
private fun Modifier.configureBackground(enabled: Boolean, isPressed: Boolean): Modifier {
  val backgroundColor = when {
    !enabled -> MaterialTheme.colorScheme.surface //todo change
    isPressed -> MaterialTheme.colorScheme.inverseSurface
    else -> MaterialTheme.colorScheme.surface
  }
  return this.background(color = backgroundColor.copy(alpha = 0.8f), shape = CircleShape)
}

@Composable
private fun getIconTint(enabled: Boolean, isPressed: Boolean) =
  when {
    !enabled -> MaterialTheme.colorScheme.inverseOnSurface //todo change
    isPressed -> MaterialTheme.colorScheme.inverseOnSurface
    else -> MaterialTheme.colorScheme.primary
  }