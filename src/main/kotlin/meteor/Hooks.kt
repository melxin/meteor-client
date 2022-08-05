package meteor

import eventbus.Events
import meteor.Main.client
import meteor.input.KeyManager
import meteor.input.MouseManager
import meteor.rs.ClientThread

import net.runelite.api.Callbacks
import net.runelite.rs.api.RSClient
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.awt.event.MouseWheelEvent
import java.awt.image.BufferedImage
import java.awt.image.VolatileImage
import org.rationalityfrontline.kevent.KEVENT as EventBus


@Suppress("UNCHECKED_CAST")
class Hooks : Callbacks {
    private var drawManager = meteor.ui.DrawManager
    private var lastStretchedDimensions: Dimension? = null
    private var stretchedImage: VolatileImage? = null
    private var stretchedGraphics: Graphics2D? = null

    class PendingEvent(val type: Enum<*>, val event: Any)

    private var pendingEvents = ArrayList<PendingEvent>()

    fun postDeferred(type: Enum<*>, event: Any) {
        pendingEvents.add(PendingEvent(type, event))
    }

    override fun drawGameImage() {
        val graphics = GameCanvas.graphics
        if (graphics == null) {
            println("graphics null")
            return
        }


/*        val graphics2d: Graphics2D = getGraphics(mainBufferProvider)

        try {
            overlayRenderer.renderOverlayLayer(graphics2d, OverlayLayer.ALWAYS_ON_TOP)
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }*/

/*        if (client.isGpu) {
            // processDrawComplete gets called on GPU by the gpu plugin at the end of its
            // drawing cycle, which is later on.
            return
        }*/

        // Stretch the game image if the user has that enabled
        if (client.gameImage == null)
        {
            println("gameImage null")
            return
        }
        val image = client.gameImage
        val finalImage: Image
        val gc: GraphicsConfiguration = GameCanvas.graphicsConfiguration
        client.stretchedDimensions = GameCanvas.size
        val stretchedDimensions: Dimension = client.stretchedDimensions
        if (lastStretchedDimensions == null || lastStretchedDimensions != stretchedDimensions) {
            /*
                Reuse the resulting image instance to avoid creating an extreme amount of objects
             */
            stretchedImage = gc
                .createCompatibleVolatileImage(stretchedDimensions.width, stretchedDimensions.height)
            stretchedGraphics?.dispose()
            stretchedGraphics = stretchedImage!!.graphics as Graphics2D
            lastStretchedDimensions = stretchedDimensions

            /*
                Fill Canvas before drawing stretched image to prevent artifacts.
            */graphics.color = Color.BLACK
            graphics.fillRect(0, 0, GameCanvas.width, GameCanvas.height)
        }
        stretchedGraphics!!.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR
        )
        stretchedGraphics!!
            .drawImage(image, 0, 0, stretchedDimensions.width, stretchedDimensions.height, null)
        finalImage = stretchedImage!!

        GameCanvas.drawGameImage(stretchedImage!!.snapshot as BufferedImage)
        // Draw the image onto the game canvas
        graphics.drawImage(finalImage, 0, 0, Main.gamePanel)

        // finalImage is backed by the client buffer which will change soon. make a copy
        // so that callbacks can safely use it later from threads.

        // finalImage is backed by the client buffer which will change soon. make a copy
        // so that callbacks can safely use it later from threads.
        drawManager.processDrawComplete { copy(finalImage) }
    }

    private fun copy(src: Image): Image {
        val width = src.getWidth(null)
        val height = src.getHeight(null)
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val graphics = image.graphics
        graphics.drawImage(src, 0, 0, width, height, null)
        graphics.dispose()
        return image
    }

    override fun mousePressed(mouseEvent: MouseEvent): MouseEvent {
        return MouseManager.processMousePressed(mouseEvent)
    }

    override fun mouseReleased(mouseEvent: MouseEvent): MouseEvent {
        return MouseManager.processMouseReleased(mouseEvent)
    }

    override fun mouseClicked(mouseEvent: MouseEvent): MouseEvent {
        return MouseManager.processMouseClicked(mouseEvent)
    }

    override fun mouseEntered(mouseEvent: MouseEvent): MouseEvent {
        return MouseManager.processMouseEntered(mouseEvent)
    }

    override fun mouseExited(mouseEvent: MouseEvent): MouseEvent {
        return MouseManager.processMouseExited(mouseEvent)
    }

    override fun mouseDragged(mouseEvent: MouseEvent): MouseEvent {
        return MouseManager.processMouseDragged(mouseEvent)
    }

    override fun mouseMoved(mouseEvent: MouseEvent): MouseEvent {
        return MouseManager.processMouseMoved(mouseEvent)
    }

    override fun mouseWheelMoved(mouseEvent: MouseWheelEvent): MouseWheelEvent {
        return MouseManager.processMouseWheelMoved(mouseEvent)
    }

    override fun keyPressed(keyEvent: KeyEvent) {
        return KeyManager.processKeyPressed(keyEvent)
    }

    override fun keyReleased(keyEvent: KeyEvent) {
        return KeyManager.processKeyReleased(keyEvent)
    }

    override fun keyTyped(keyEvent: KeyEvent) {
        return KeyManager.processKeyTyped(keyEvent)
    }


}