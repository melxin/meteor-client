package meteor.rs

import net.runelite.api.Client
import java.applet.Applet

class Applet {

    companion object {
        var panelSize = 375
        lateinit var applet: Applet
        var mainThread: Thread? = null
        var clientThread: Thread? = null

        fun asClient(applet: Applet): Client {
            return applet as Client
        }
    }

    fun init() {
        applet = configureApplet()
        applet.size = applet.minimumSize
    }

    private fun configureApplet(): Applet {
        val applet = ClassLoader.getSystemClassLoader().loadClass("Client").newInstance() as Applet
        /*        applet.setStub(this)
                applet.maximumSize = appletMaxSize()
                applet.minimumSize = appletMinSize()
                applet.preferredSize = applet.minimumSize*/
        return applet
    }
}