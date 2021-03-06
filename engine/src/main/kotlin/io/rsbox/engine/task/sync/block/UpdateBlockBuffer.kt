package io.rsbox.engine.task.sync.block

import io.rsbox.engine.model.ChatMessage
import io.rsbox.engine.model.ForcedMovement
import io.rsbox.engine.model.Hit

/**
 * @author Kyle Escobar
 */

class UpdateBlockBuffer {
    private var mask = 0

    var teleport = false

    var forceChat = ""
    lateinit var publicChat: ChatMessage

    var faceAngle = 0
    var faceEntityIndex = -1

    var animation = 0
    var animationDelay = 0

    var graphicId = 0
    var graphicHeight = 0
    var graphicDelay = 0

    lateinit var forcedMovement: ForcedMovement

    val hits = mutableListOf<Hit>()

    fun isDirty(): Boolean = mask != 0

    fun clean() {
        mask = 0
        teleport = false
    }

    fun addBit(bit: Int) {
        mask = mask or bit
    }

    fun hasBit(bit: Int): Boolean {
        return (mask and bit) != 0
    }

    fun blockValue(): Int = mask
}