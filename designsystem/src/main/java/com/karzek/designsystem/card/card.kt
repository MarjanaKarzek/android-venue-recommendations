package com.karzek.designsystem.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.karzek.designsystem.R
import com.karzek.designsystem.button.IconButton
import com.karzek.designsystem.decoration.surfaceGradient
import com.karzek.designsystem.token.RadiusToken
import com.karzek.designsystem.token.SpacingToken.X0_5
import com.karzek.designsystem.token.SpacingToken.X1
import com.karzek.designsystem.token.SpacingToken.X3

private const val DESCRIPTION_MAX_LINES: Int = 2

data class CardData(
  val title: String,
  val description: String?,
  val imageUrl: String,
  @DrawableRes val icon: Int,
  val onIconClicked: () -> Unit,
)

@Composable
fun Card(
  data: CardData,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(RadiusToken.X1)
  ) {
    Box {
      AsyncImage(
        model = data.imageUrl,
        contentDescription = "",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop,
      )
      IconButton(
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(X1),
        icon = data.icon,
        contentDescription = "",
        onClick = data.onIconClicked
      )
      CardBody(data)
    }
  }
}

@Composable
private fun BoxScope.CardBody(data: CardData) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .surfaceGradient()
      .align(Alignment.BottomCenter)
  ) {
    Text(
      modifier = Modifier.padding(start = X1, end = X1, top = X3),
      text = data.title,
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.onSurface,
    )
    data.description?.let {
      Text(
        modifier = Modifier.padding(start = X1, end = X1, top = X0_5),
        text = it,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface,
        maxLines = DESCRIPTION_MAX_LINES,
        overflow = TextOverflow.Ellipsis,
      )
    }
    Spacer(modifier = Modifier.height(X1))
  }
}

@Preview
@Composable
fun CardPreview() {
  Card(
    CardData(
      title = "Taco Bell Tennispalatsi",
      description = null,
      imageUrl = "",
      icon = R.drawable.ic_heart_filled,
      onIconClicked = {},
    )
  )
}

@Preview
@Composable
fun CardDescriptionPreview() {
  Card(
    CardData(
      title = "Taco Bell Tennispalatsi",
      imageUrl = "",
      description = "This is a long text that shows how the max lines property is used and demonstrates" +
          " what happens in case the line is too long.",
      icon = R.drawable.ic_heart_filled,
      onIconClicked = {},
    )
  )
}