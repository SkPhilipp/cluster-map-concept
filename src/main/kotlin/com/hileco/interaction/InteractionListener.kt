package com.hileco.interaction

import com.hileco.model.Entity
import com.hileco.model.Game

class InteractionListener(
    private val game: Game,
    private val controlledEntity: Entity
) {
    private val collisions = Collisions()

    fun moveHorizontal(difference: Int) {
        val collisionCount = game.clusterMap.clusterOf(controlledEntity)
            .distinct()
            .filter { it != controlledEntity }
            .count { collisions.wouldCollide(controlledEntity, difference, 0, it) }
        if (collisionCount == 0) {
            game.clusterMap.update(controlledEntity) {
                controlledEntity.x += difference
            }
        }
    }

    fun moveVertical(difference: Int) {
        val collisionCount = game.clusterMap.clusterOf(controlledEntity)
            .distinct()
            .filter { it != controlledEntity }
            .count { collisions.wouldCollide(controlledEntity, 0, difference, it) }
        if (collisionCount == 0) {
            game.clusterMap.update(controlledEntity) {
                controlledEntity.y += difference
            }
        }
    }

    fun target(x: Int, y: Int) {
        game.targetLocation = x to y
    }

    fun trigger(x: Int, y: Int) {
        game.clusterMap.clusterOf(x, y)
            .filter { it != controlledEntity }
            .filter { collisions.isOn(x, y, it) }
            .filter { collisions.distanceBetween(it, game.localPlayer) < 50 }
            .forEach { entity ->
                game.clusterMap.remove(entity)
                game.entities.remove(entity)
            }
    }
}
