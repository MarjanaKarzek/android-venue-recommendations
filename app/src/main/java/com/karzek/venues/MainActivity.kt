package com.karzek.venues

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.karzek.designsystem.card.Card
import com.karzek.designsystem.theme.AppTheme
import com.karzek.designsystem.token.SpacingToken.X1
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

  private val viewModel: MainViewModel by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      AppTheme {
        val state = viewModel.viewState.collectAsStateWithLifecycle()

        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
          Column(
            modifier = Modifier
              .padding(paddingValues)
              .verticalScroll(rememberScrollState())
          ) {
            state.value.forEach {
              Card(modifier = Modifier.padding(X1), data = it)
            }
          }
        }
      }
    }
  }
}