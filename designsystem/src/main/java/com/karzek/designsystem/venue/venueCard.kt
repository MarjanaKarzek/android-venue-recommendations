package com.karzek.designsystem.venue

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.karzek.designsystem.X0_5
import com.karzek.designsystem.X1
import com.karzek.designsystem.button.WishIconButton
import com.karzek.designsystem.typography.body
import com.karzek.designsystem.typography.title

data class VenueCardData(
  val name: String,
  val shortDescription: String?,
  val imageUrl: String,
  val isWishListed: Boolean,
  val onWishClick: () -> Unit,
)

@Composable
fun VenueCard(
  data: VenueCardData,
  modifier: Modifier = Modifier,
) {
  Card(modifier = modifier.fillMaxWidth()) {
    Box {
      AsyncImage(
        model = data.imageUrl,
        contentDescription = "",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop,
      )
      WishIconButton(
        modifier = modifier.align(Alignment.TopEnd),
        isWishListed = data.isWishListed,
        contentDescription = "",
        onClick = data.onWishClick
      )
      VenueDescription(data)
    }
  }
}

@Composable
private fun BoxScope.VenueDescription(data: VenueCardData) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(X1)
      .align(Alignment.BottomCenter)
  ) {
    Text(text = data.name, style = MaterialTheme.typography.title)
    data.shortDescription?.let {
      Text(
        modifier = Modifier.padding(top = X0_5),
        text = it,
        style = MaterialTheme.typography.body
      )
    }
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
      onWishClick = {},
    )
  )
}