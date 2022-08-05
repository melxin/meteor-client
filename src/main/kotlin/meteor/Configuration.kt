package meteor

import java.io.File

object Configuration {
    val METEOR_DIR = File(System.getProperty("user.home"), ".meteor274")
    var CONFIG_FILE = File(METEOR_DIR, "settings.properties")
    const val MASTER_GROUP = "MeteorLite"
    var BLOCK_MOUSE_4_PLUS = true
    var allowGPU = true
}