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
        const val BLOCK_SIZE = 10
        val BLOCK_STONE = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(143, 143, 143), "Stone")
        val BLOCK_CLAY = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(90, 35, 35), "Clay")
        val BLOCK_DIRT = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(140, 90, 0), "Dirt")
        val BLOCK_COPPER = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(175, 77, 35), "Copper")
        val BLOCK_TIN = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(196, 196, 196), "Tin")
        val BLOCK_SILVER = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(220, 220, 220), "Silver")
        val BLOCK_GOLD = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(190, 170, 0), "Gold")
        val BLOCK_LEAD = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(196, 196, 240), "Lead")
        val BLOCK_SAPPHIRE = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(95, 150, 240), "Sapphire")
        val BLOCK_EMERALD = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(100, 240, 152), "Emerald")
        val BLOCK_RUBY = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(240, 76, 76), "Ruby")
        val BLOCK_DIAMOND = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(230, 230, 230), "Diamond")
        val CHEST = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(200, 183, 36), "Chest")
        val PLAYER = EntityType(BLOCK_SIZE, BLOCK_SIZE * 2, Color(255, 0, 0), "Player")
    }
}
