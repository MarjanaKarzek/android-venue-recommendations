package com.karzek.venues.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

  private val viewModel: MainViewModel by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val state = viewModel.viewState.collectAsStateWithLifecycle()
      MainView(state.value.data)
    }
  }
}