package com.karzek.designsystem.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

internal val Typography.title: TextStyle
  get() = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = TextSize.TITLE,
    lineHeight = LineHeight.TITLE,
  )

internal val Typography.body: TextStyle
  get() = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = TextSize.BODY,
    lineHeight = LineHeight.BODY,
  )

internal val Typography.caption: TextStyle
  get() = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = TextSize.CAPTION,
    lineHeight = LineHeight.CAPTION,
  )
