package com.karzek.venues.ui

import androidx.annotation.StringRes
import com.karzek.designsystem.card.CardData

data class MainViewState(
  val isLoading: Boolean = true,
  val data: List<CardData> = emptyList(),
  @StringRes val error: Int? = null,
)