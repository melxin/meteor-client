package meteor.plugins


import eventbus.Events
import meteor.Main
import meteor.events.PluginChanged
import org.rationalityfrontline.kevent.KEvent
import org.rationalityfrontline.kevent.KEventSubscriber
import org.rationalityfrontline.kevent.unsubscribeAll
import org.rationalityfrontline.kevent.subscribe as kSubscribe

open class EventSubscriber : KEventSubscriber {
    var eventListening: Boolean = false

    open fun onPluginChanged(it: PluginChanged) {}

    open fun executeIfListening(unit: () -> (Unit)) {
        if (eventListening)
            unit()
    }

    private fun subscribeEvents() {
        subscribeEvent<PluginChanged>(Events.PLUGIN_CHANGED) { executeIfListening { onPluginChanged(it) } }
    }

    private inline fun <reified T : Any> subscribeEvent(type: Enum<*>, noinline unit: (T) -> Unit) {
        kSubscribe<T>(type) { event -> unit.invoke(event.data) }
    }


    fun unsubscribe() {
        unsubscribeAll()
        eventListening = false
    }

    fun subscribe() {
        subscribeEvents()

        //Plugin eventListening is handled by PluginManager plugin start/stop
        if (this !is Plugin)
            eventListening = true
    }

    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    val randomString = (1..10)
        .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")

    override val SUBSCRIBER_TAG: String
        get() = randomString

    override val KEVENT_INSTANCE: KEvent
        get() = Main.eventBus
}