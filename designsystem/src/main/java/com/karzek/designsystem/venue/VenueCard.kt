package com.karzek.designsystem.venue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.karzek.designsystem.R
import com.karzek.designsystem.X1
import com.karzek.designsystem.typography.body
import com.karzek.designsystem.typography.title

data class VenueCardData(
  val name: String,
  val shortDescription: String?,
  val imageUrl: String,
  val isWishListed: Boolean,
)

@Composable
fun VenueCard(
  data: VenueCardData,
  modifier: Modifier = Modifier,
) {
  Card(modifier = modifier.fillMaxWidth()) {
    Column(modifier = Modifier.padding(X1)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(text = data.name, style = MaterialTheme.typography.title)
        WishIconButton(data.isWishListed, "") {}
      }
      data.shortDescription?.let {
        Text(text = it, style = MaterialTheme.typography.body)
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