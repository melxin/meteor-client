package meteor.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import meteor.GameCanvas
import meteor.Main
import meteor.rs.Applet
import net.runelite.rs.api.RSClient
import net.runelite.rs.api.RSGame

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.image.BufferedImage
import javax.swing.JPanel


var loaded = false
var applet = java.applet.Applet()

@Composable
fun OSRSPanel() {
    val mod: Modifier = Modifier.fillMaxWidth().fillMaxHeight().onSizeChanged { Main.client.stretchedDimensions = Dimension(it.width, it.height) }
    Spacer(modifier = Modifier.width(Main.meteorConfig.toolbarWidth().dp))
    SwingPanel(
        Color.Black,
        modifier = mod,
        factory = {
            JPanel().apply {
                layout = BorderLayout()
                add(GameCanvas, BorderLayout.CENTER)
                GameCanvas.isVisible = true
                isVisible = true
                if (!loaded) {
                    applet = Applet.applet
                    Applet.applet.init()
                    Applet.applet.start()
                    Main.finishStartup()
                    loaded = true
                } else {
                    Main.gamePanel = this
                    Main.gamePanel?.graphics?.let {
                        Main.graphics = it
                    }
                }
            }
        },
    update = {
        GameCanvas.size = it.size
        Main.client.stretchedDimensions = it.size
    })
}
