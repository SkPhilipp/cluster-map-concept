package com.hileco.model

import com.hileco.interaction.Area
import com.hileco.interaction.Clusterable
import java.awt.Color

data class Entity(
    var x: Int,
    var y: Int,
    val type: EntityType
) : Clusterable {
    override fun area(): Area {
        return Area(x, y, x + type.width, y + type.height)
    }
}

data class EntityType(
    val width: Int,
    val height: Int,
    val color: Color = Color.BLACK,
    val name: String = "Unknown"
)

class EntityTypes {
    companion object {
        const val BLOCK_SIZE = 12
        val BLOCK_BROWN = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(90, 35, 35), "Clay")
        val BLOCK_GREEN = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(0, 140, 0), "Dirt")
        val PLAYER = EntityType(BLOCK_SIZE, BLOCK_SIZE * 2, Color(255, 0, 0), "Player")
    }
}
