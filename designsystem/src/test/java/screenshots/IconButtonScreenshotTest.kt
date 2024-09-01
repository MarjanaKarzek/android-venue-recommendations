package screenshots

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.android.resources.NightMode
import com.karzek.designsystem.R
import com.karzek.designsystem.button.IconButton
import com.karzek.designsystem.theme.AppTheme
import com.karzek.designsystem.token.SpacingToken.X1
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import screenshots.util.MockedPressedInteractionSource
import screenshots.util.ScreenshotHelper

@RunWith(Parameterized::class)
class IconButtonScreenshotTest(private val config: Companion.Config) : ScreenshotHelper(config) {

  @Test
  fun `IconButton states`() {
    paparazzi.snapshot {
      AppTheme(darkTheme = config.deviceConfig.nightMode == NightMode.NIGHT) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.inversePrimary)
        ) {
          Text(
            text = "enabled",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
          )
          IconButton(
            icon = R.drawable.ic_heart_filled,
            enabled = true,
            contentDescription = "",
            onClick = { },
          )
          Text(
            text = "disabled",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
          )
          IconButton(
            icon = R.drawable.ic_heart_filled,
            enabled = false,
            contentDescription = "",
            onClick = { },
          )
          Text(
            text = "pressed",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
          )
          IconButton(
            icon = R.drawable.ic_heart_filled,
            enabled = true,
            contentDescription = "",
            onClick = { },
            interactionSource = MockedPressedInteractionSource,
          )
        }
      }
    }
  }
}
