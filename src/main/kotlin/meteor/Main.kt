package meteor

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.*
import meteor.config.ConfigManager
import meteor.config.MeteorConfig

import meteor.game.FontManager
import meteor.plugins.EventSubscriber
import meteor.plugins.PluginManager
import meteor.rs.Applet
import meteor.rs.AppletConfiguration
import meteor.ui.composables.Window
import meteor.ui.composables.dev.UI

import meteor.ui.themes.MeteorliteTheme
import meteor.util.ExecutorServiceExceptionLogger
import net.runelite.api.Callbacks
import net.runelite.api.Client
import net.runelite.http.api.xp.XpClient
import net.runelite.rs.api.RSClient
import okhttp3.OkHttpClient
import org.apache.commons.lang3.time.StopWatch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

import org.koin.core.context.startKoin
import java.awt.Graphics
import java.net.Authenticator
import java.net.PasswordAuthentication
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.swing.JPanel

import kotlin.system.exitProcess
import org.rationalityfrontline.kevent.KEVENT as EventBus

object Main : ApplicationScope, KoinComponent, EventSubscriber() {
    lateinit var frame: FrameWindowScope
    var meteorConfig: MeteorConfig = ConfigManager.getConfig(MeteorConfig::class.java)!!

    init {
        ConfigManager.loadSavedProperties()
        ConfigManager.setDefaultConfiguration(meteorConfig.javaClass, false)
        ConfigManager.saveProperties()
    }

    val eventBus = EventBus
    lateinit var client: Client
    lateinit var callbacks: Callbacks
    var gamePanel: JPanel? = null
    var graphics: Graphics? = null
    val httpClient = OkHttpClient()
    val xpClient = XpClient(httpClient)
    val fontManager = FontManager
    val executor = ExecutorServiceExceptionLogger(Executors.newSingleThreadScheduledExecutor())

    var logger = Logger()

    var placement: WindowPlacement = WindowPlacement.Maximized

    private val timer = StopWatch()

    @JvmStatic
    fun main(args: Array<String>) = application {
        timer.start()
        processArguments(args)
        startKoin { modules(Module.CLIENT_MODULE) }
        callbacks = get()
        //MeteorliteTheme.install()
        AppletConfiguration.init()
        Applet().init()
        Window(
            onCloseRequest = this::exitApplication,
            title = "Meteor",
            icon = painterResource("Meteor_icon.png"),
            state = rememberWindowState(placement = WindowPlacement.Maximized),
            content = { this.Window() } //::finishStartup is called at the end of this function
        )

    }

    class ProxyAuth constructor(user: String, password: String?) : Authenticator() {
        val auth: PasswordAuthentication

        init {
            auth = PasswordAuthentication(user, password?.toCharArray() ?: charArrayOf())
        }

        override fun getPasswordAuthentication(): PasswordAuthentication {
            return auth
        }
    }

    fun finishStartup() {
        client = Applet.asClient(Applet.applet)
        client.callbacks = callbacks
        GameCanvas.addMouseListener((client as RSClient).gameWindow.`game$api`())
        GameCanvas.addMouseMotionListener((client as RSClient).gameWindow.`game$api`())
        GameCanvas.addKeyListener((client as RSClient).gameWindow.`game$api`())
        GameCanvas.addFocusListener((client as RSClient).gameWindow.`game$api`())
        //initOverlays()
        PluginManager.loadExternalPlugins()
        timer.stop()
        logger.info("Meteor started in ${timer.getTime(TimeUnit.MILLISECONDS)}ms")
    }

    fun processArguments(args: Array<String>) {
        for (arg in args) {
            when (arg.lowercase()) {
                "disableGPU".lowercase() -> {
                    Configuration.allowGPU = false
                }
            }
        }
    }

    /**
     * Save and exit
     */
    override fun exitApplication() {
        PluginManager.shutdown()
        ConfigManager.saveProperties()
        exitProcess(0)
    }
}