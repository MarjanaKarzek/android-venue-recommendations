package screenshots.util

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runners.Parameterized
import java.util.Locale

abstract class ScreenshotHelper(config: Config) {

  private val originalLocale = Locale.getDefault()

  @Before
  open fun setUp() {
    Locale.setDefault(Locale("en", "US"))
  }

  @After
  open fun tearDown() {
    Locale.setDefault(originalLocale)
  }

  companion object {
    @JvmStatic
    @Parameterized.Parameters(name = "Config:{0}")
    fun data(): Collection<Config> {
      return listOf(
        Config.NEXUS_5_DAY,
        Config.NEXUS_5_NIGHT,
      )
    }

    enum class Config(
      val deviceConfig: DeviceConfig,
    ) {
      NEXUS_5_DAY(
        deviceConfig = DeviceConfig.NEXUS_5.copy(softButtons = false, nightMode = NightMode.NOTNIGHT),
      ),
      NEXUS_5_NIGHT(
        deviceConfig = DeviceConfig.NEXUS_5.copy(softButtons = false, nightMode = NightMode.NIGHT),
      ),
    }
  }

  @get:Rule
  val paparazzi = Paparazzi(
    deviceConfig = config.deviceConfig,
    theme = "AppTheme",
    maxPercentDifference = 0.01,
    renderingMode = SessionParams.RenderingMode.SHRINK,
  )
}