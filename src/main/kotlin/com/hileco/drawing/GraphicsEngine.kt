package com.hileco.drawing

import com.hileco.interaction.Collisions
import com.hileco.model.Game
import java.awt.Color
import java.awt.Graphics

class GraphicsEngine(private val game: Game) {

    private val collisions = Collisions()

    fun draw(bufferGraphics: Graphics) {
        /**
         * Draw backgrounds
         */
        bufferGraphics.color = Color.blue
        bufferGraphics.fillRect(0, 0, 1400, 100)
        bufferGraphics.color = Color.darkGray
        bufferGraphics.fillRect(0, 100, 1400, 300)
        bufferGraphics.color = Color.black
        bufferGraphics.fillRect(0, 300, 1400, 1000)
        /**
         * Draw entities
         */
        game.entities.forEach {
            bufferGraphics.color = it.type.color
            bufferGraphics.fillRect(it.x, it.y, it.type.width, it.type.height)
        }
        /**
         * Highlight collisions around the local player.
         */
        game.clusterMap.clusterOf(game.localPlayer).forEach {
            if (it != game.localPlayer) {
                val colliding = collisions.areColliding(it, game.localPlayer)
                bufferGraphics.color = if (colliding) Color.blue else Color.yellow
                bufferGraphics.drawRect(it.x, it.y, it.type.width, it.type.height)
            }
        }
        /**
         * Highlight collisions around the target.
         */
        val targetLocation = game.targetLocation
        if (targetLocation != null) {
            game.clusterMap.clusterOf(targetLocation.first, targetLocation.second).forEach {
                val colliding = collisions.isOn(targetLocation.first, targetLocation.second, it)
                bufferGraphics.color = if (colliding) Color.blue else Color.yellow
                bufferGraphics.drawRect(it.x, it.y, it.type.width, it.type.height)
            }
        }
    }
}
