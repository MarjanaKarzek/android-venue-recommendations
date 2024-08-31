package com.karzek.designsystem.venue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.karzek.designsystem.R

data class VenueCardData(
  val name: String,
  val shortDescription: String?,
  val imageUrl: String,
  val isWishListed: Boolean,
)

@Composable
fun VenueCard(data: VenueCardData) {
  Card(modifier = Modifier.fillMaxWidth()) {
    Column {
      Row {
        Text(text = data.name)
        WishIconButton(data.isWishListed, "") {}
      }
      data.shortDescription?.let {
        Text(text = it)
      }
    }
  }
}

@Composable
fun WishIconButton(isWishListed: Boolean, contentDescription: String, onClick: () -> Unit) {
  val icon = if (isWishListed) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined
  IconButton(onClick = onClick) {
    Icon(painter = painterResource(id = icon), contentDescription = contentDescription)
  }
}

@Preview
@Composable
fun VenueCardPreview() {
  VenueCard(
    VenueCardData(
      name = "Taco Bell Tennispalatsi",
      shortDescription = null,
      imageUrl = "",
      isWishListed = true,
    )
  )
}