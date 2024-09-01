package screenshots

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import com.android.resources.NightMode
import com.karzek.designsystem.R
import com.karzek.designsystem.button.IconButton
import com.karzek.designsystem.theme.AppTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import screenshots.util.ScreenshotHelper

@RunWith(Parameterized::class)
class IconButtonScreenshotTest(private val config: Companion.Config) : ScreenshotHelper(config) {

  @Test
  fun `IconButton states`() {
    paparazzi.snapshot {
      AppTheme(darkTheme = config.deviceConfig.nightMode == NightMode.NIGHT) {
        Column(modifier = Modifier.fillMaxWidth()) {
          IconButton(icon = R.drawable.ic_heart_filled, contentDescription = "", onClick = { })
        }
      }
    }
  }
}