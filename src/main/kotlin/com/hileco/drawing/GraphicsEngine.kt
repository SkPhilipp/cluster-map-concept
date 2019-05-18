package com.hileco.drawing

import com.hileco.interaction.Collisions
import com.hileco.model.Game
import java.awt.Color
import java.awt.Graphics

class GraphicsEngine(private val game: Game) {

    private val collisions = Collisions()

    fun draw(bufferGraphics: Graphics) {
        game.entities.forEach {
            bufferGraphics.color = it.entityType.color
            bufferGraphics.fillRect(it.x, it.y, it.entityType.width, it.entityType.height)
        }
        game.clusterMap.clusterOf(game.localPlayer).forEach {
            if (it != game.localPlayer) {
                val colliding = collisions.areColliding(it, game.localPlayer)
                bufferGraphics.color = if (colliding) Color.blue else Color.yellow
                bufferGraphics.drawRect(it.x, it.y, it.entityType.width, it.entityType.height)
            }
        }
    }
}
