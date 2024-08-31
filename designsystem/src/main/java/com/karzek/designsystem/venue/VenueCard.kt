package com.karzek.designsystem.venue

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

data class VenueCardData(
  val name: String,
  val shortDescription: String?,
  val imageUrl: String,
  val isWishListed: Boolean,
)

@Composable
fun VenueCard(data: VenueCardData) {
  Row(modifier = Modifier.fillMaxWidth()) {
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = data.name
    )
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