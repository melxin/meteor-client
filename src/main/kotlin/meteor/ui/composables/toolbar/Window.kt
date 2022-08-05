package meteor.ui.composables.dev

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import meteor.Main
import meteor.ui.composables.darkThemeColors
import meteor.ui.composables.toolbar.ToolbarPanel


@DslMarker
annotation class ToolBar

@DslMarker
annotation class WindowPanel

@DslMarker
annotation class GamePanel

class GameWindow {
    @GamePanel
    @Composable
    fun gameFrame(
        backgroundColor: Color = darkThemeColors.background,
        content: @Composable () -> Unit
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            BoxWithConstraints(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(backgroundColor)
            ) {
                gameFrame = this
                Column {
                    content.invoke()
                }
            }
        }
    }

    companion object {
        var gameFrame: BoxWithConstraintsScope? = null
    }
}


class UI {
    @ToolBar
    @Composable
    fun toolBar(content: @Composable () -> Unit) {

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxHeight()
                .width(Main.meteorConfig.toolbarWidth().dp)
                .background(Color.Black)
        ) {

            mutableStateOf(ToolbarPanel())
            content.invoke()
        }
    }

    @WindowPanel
    @Composable
    fun content(
        content: @Composable GameWindow.() -> Unit
    ) {
        val gameContent = GameWindow()

        BoxWithConstraints(modifier = Modifier.background(darkThemeColors.background)) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                content.invoke(gameContent)
            }
        }

    }

}

@Composable
fun WindowFrame(
    modifier: Modifier = Modifier,
    content: @Composable UI.() -> Unit
) {
    val ui = UI()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(darkThemeColors.background)
        ) {
            content.invoke(ui)
        }
    }

}