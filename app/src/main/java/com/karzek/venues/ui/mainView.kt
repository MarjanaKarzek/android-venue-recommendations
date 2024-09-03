package com.karzek.venues.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.karzek.designsystem.banner.Banner
import com.karzek.designsystem.banner.BannerVariant
import com.karzek.designsystem.card.Card
import com.karzek.designsystem.token.SpacingToken.X1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(state: MainViewState, modifier: Modifier = Modifier) {
  Column(modifier = modifier) {
    state.error?.let {
      Banner(state.error, variant = BannerVariant.ERROR)
    }
    LazyColumn {
      items(items = state.data, key = { it.title }) {
        Card(
          modifier = Modifier
            .padding(X1)
            .animateItemPlacement(),
          data = it,
        )
      }
    }
  }
}