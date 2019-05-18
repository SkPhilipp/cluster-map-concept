package com.hileco.generation

import com.hileco.model.Entity
import com.hileco.model.EntityType
import com.hileco.model.EntityTypes.Companion.BLOCK_BROWN
import com.hileco.model.EntityTypes.Companion.BLOCK_GREEN
import com.hileco.model.EntityTypes.Companion.BLOCK_SIZE
import kotlin.random.Random

class TerrainGenerator(seed: Int) {
    private val random = Random(seed)

    private fun generateEntityType(): EntityType {
        return when (random.nextInt(0, 2)) {
            0 -> BLOCK_BROWN
            1 -> BLOCK_GREEN
            else -> throw RuntimeException()
        }
    }

    fun generate(xOffset: Int, yOffset: Int, terrainWidth: Int, terrainHeight: Int): ArrayList<Entity> {
        val entities = arrayListOf<Entity>()
        for (y in 0..terrainHeight) {
            for (x in 0..terrainWidth) {
                val entity = Entity(
                    xOffset + BLOCK_SIZE * x,
                    yOffset + BLOCK_SIZE * y,
                    generateEntityType()
                )
                entities.add(entity)
            }
        }
        return entities
    }
}