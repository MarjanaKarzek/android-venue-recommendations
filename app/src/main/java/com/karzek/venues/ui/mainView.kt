package com.karzek.venues.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.karzek.designsystem.card.Card
import com.karzek.designsystem.card.CardData
import com.karzek.designsystem.theme.AppTheme
import com.karzek.designsystem.token.SpacingToken.X1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(state: List<CardData>) {
  AppTheme {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
      LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(items = state, key = { it.title }) {
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
}