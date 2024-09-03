package com.karzek.venues.ui

import com.karzek.designsystem.card.CardData

data class MainViewState(
  val isLoading: Boolean = true,
  val data: List<CardData> = emptyList(),
  val message: String? = null,
  )