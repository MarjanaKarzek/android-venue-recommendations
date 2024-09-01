package com.karzek.designsystem.button

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun IconButton(
  @DrawableRes icon: Int,
  contentDescription: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  IconButton(modifier = modifier, onClick = onClick) {
    Icon(
      painter = painterResource(id = icon),
      contentDescription = contentDescription,
      tint = MaterialTheme.colorScheme.onPrimary,
    )
  }
}