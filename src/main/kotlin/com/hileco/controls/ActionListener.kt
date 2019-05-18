package com.hileco.controls

import com.hileco.interaction.Collisions
import com.hileco.model.Entity
import com.hileco.model.Game

class ActionListener(
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
            controlledEntity.x += difference
        }
    }

    fun moveVertical(difference: Int) {
        val collisionCount = game.clusterMap.clusterOf(controlledEntity)
            .distinct()
            .filter { it != controlledEntity }
            .count { collisions.wouldCollide(controlledEntity, 0, difference, it) }
        if (collisionCount == 0) {
            controlledEntity.y += difference
        }
    }
}
