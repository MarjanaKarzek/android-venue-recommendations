package com.karzek.designsystem.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.karzek.designsystem.R

@Composable
fun WishIconButton(
  isWishListed: Boolean,
  contentDescription: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val icon = if (isWishListed) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined
  IconButton(modifier = modifier, onClick = onClick) {
    Icon(painter = painterResource(id = icon), contentDescription = contentDescription)
  }
}