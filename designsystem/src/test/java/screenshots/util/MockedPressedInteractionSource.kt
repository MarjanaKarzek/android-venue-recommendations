package screenshots.util

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object MockedPressedInteractionSource : MutableInteractionSource {
  override val interactions: Flow<Interaction>
    get() = flowOf(PressInteraction.Press(Offset(10f, 10f)))

  override suspend fun emit(interaction: Interaction) {}

  override fun tryEmit(interaction: Interaction): Boolean {
    return false
  }
}