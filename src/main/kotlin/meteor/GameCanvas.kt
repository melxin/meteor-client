package meteor

import java.awt.Canvas
import java.awt.image.BufferedImage

object GameCanvas : Canvas() {
    fun drawGameImage(gameImage: BufferedImage) {
        if (graphics != null) {
            graphics.drawImage(gameImage, 0, 0, parent)
        }
    }
}