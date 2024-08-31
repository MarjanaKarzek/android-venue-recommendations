package com.karzek.venues

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.karzek.designsystem.X1
import com.karzek.designsystem.theme.VenueRecommendationsTheme
import com.karzek.designsystem.venue.VenueCard
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

  private val viewModel: MainViewModel by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      VenueRecommendationsTheme {
        val state = viewModel.viewState.collectAsStateWithLifecycle()

        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
          Column(modifier = Modifier.padding(paddingValues)) {
            state.value.forEach {
              VenueCard(modifier = Modifier.padding(X1), data = it)
            }
          }
        }
      }
    }
    viewModel.handleWishlist()
    viewModel.handleLocation()
  }
}