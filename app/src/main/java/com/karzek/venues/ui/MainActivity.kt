package com.karzek.venues.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.karzek.designsystem.loading.Loading
import com.karzek.designsystem.theme.AppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

  private val viewModel: MainViewModel by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val state = viewModel.viewState.collectAsStateWithLifecycle()
      AppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
          when {
            state.value.isLoading -> Loading(
              modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
            )

            else -> MainView(
              modifier = Modifier.padding(paddingValues = paddingValues),
              state = state.value,
            )
          }
        }
      }
    }
  }

  override fun onPause() {
    super.onPause()
    viewModel.stopRestaurantFetching()
    viewModel.scheduleBackgroundWork()
  }

  override fun onResume() {
    super.onResume()
    viewModel.cancelBackgroundWork()
    viewModel.startRestaurantFetching()
  }
}