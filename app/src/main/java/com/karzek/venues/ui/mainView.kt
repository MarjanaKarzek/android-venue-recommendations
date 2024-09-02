package com.karzek.venues.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.karzek.designsystem.card.Card
import com.karzek.designsystem.card.CardData
import com.karzek.designsystem.theme.AppTheme
import com.karzek.designsystem.token.SpacingToken.X1

@Composable
fun MainView(state: List<CardData>) {
  AppTheme {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
      Column(
        modifier = Modifier
          .padding(paddingValues)
          .verticalScroll(rememberScrollState())
      ) {
        state.forEach {
          Card(modifier = Modifier.padding(X1), data = it)
        }
      }
    }
  }
}