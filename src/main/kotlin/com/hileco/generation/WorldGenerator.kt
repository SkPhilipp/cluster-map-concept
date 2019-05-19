package com.hileco.generation

import com.hileco.model.Entity
import com.hileco.model.EntityType
import com.hileco.model.EntityTypes.Companion.BLOCK_CLAY
import com.hileco.model.EntityTypes.Companion.BLOCK_COPPER
import com.hileco.model.EntityTypes.Companion.BLOCK_DIRT
import com.hileco.model.EntityTypes.Companion.BLOCK_GOLD
import com.hileco.model.EntityTypes.Companion.BLOCK_LEAD
import com.hileco.model.EntityTypes.Companion.BLOCK_SILVER
import com.hileco.model.EntityTypes.Companion.BLOCK_SIZE
import com.hileco.model.EntityTypes.Companion.BLOCK_STONE
import com.hileco.model.EntityTypes.Companion.BLOCK_TIN
import com.hileco.model.EntityTypes.Companion.CHEST
import kotlin.random.Random

typealias Terrain = Array<Array<EntityType?>>

class WorldGenerator(seed: Int) {
    private val random = Random(seed)

    private data class Cavern(
        val blockOffsetX: Int,
        val blockOffsetY: Int,
        val cavernHeight: Int,
        val cavernWidth: Int
    )

    private fun randomTerrainType(): EntityType {
        return when (random.nextInt(0, 3)) {
            0 -> BLOCK_CLAY
            1 -> BLOCK_DIRT
            2 -> BLOCK_STONE
            else -> throw RuntimeException()
        }
    }

    private fun randomOreType(): EntityType {
        return when (random.nextInt(0, 5)) {
            0 -> BLOCK_COPPER
            1 -> BLOCK_TIN
            2 -> BLOCK_SILVER
            3 -> BLOCK_GOLD
            4 -> BLOCK_LEAD
            else -> throw RuntimeException()
        }
    }

    private fun randomLootType(): EntityType {
        return when (random.nextInt(0, 1)) {
            0 -> CHEST
            else -> throw RuntimeException()
        }
    }

    fun generate(xOffset: Int, yOffset: Int, blockWidth: Int, blockHeight: Int): ArrayList<Entity> {
        val terrain = Terrain(blockHeight) { Array(blockWidth) { null } }
        terrain.forEachIndexed { y: Int, terrainLine: Array<EntityType?> ->
            terrainLine.forEachIndexed { x: Int, _: EntityType? ->
                terrainLine[x] = randomTerrainType()
            }
        }
        val deposits = 0..blockWidth * blockHeight / 20 / 20
        for (i in deposits) {
            generateOreDeposit(terrain, random.nextInt(0, blockWidth), random.nextInt(0, blockHeight))
        }
        val caverns = (0..blockWidth * blockHeight / 40 / 40)
            .map {
                val cavernHeight = random.nextInt(3, 10)
                val cavernWidth = random.nextInt(8, 22)
                Cavern(
                    random.nextInt(cavernWidth + 1, blockWidth - 1) - cavernWidth,
                    random.nextInt(cavernHeight + 1, blockHeight - 1) - cavernHeight,
                    cavernHeight,
                    cavernWidth
                )
            }

        caverns.forEach {
            generateCavern(terrain, it)
        }
        caverns.forEach {
            generateCavernEntry(terrain, it)
        }
        caverns.forEach {
            generateCavernLoot(terrain, it)
        }
        return build(xOffset, yOffset, terrain)
    }

    private fun generateOreDeposit(
        terrain: Terrain,
        blockOffsetX: Int,
        blockOffsetY: Int,
        depositSize: Int = random.nextInt(2, 6),
        depositType: EntityType = randomOreType()
    ) {
        for (y in blockOffsetY..Math.min(terrain.size - 1, blockOffsetY + depositSize)) {
            val line = terrain[y]
            for (x in blockOffsetX..Math.min(line.size - 1, blockOffsetX + depositSize)) {
                line[x] = depositType
            }
        }
    }

    private fun generateCavern(terrain: Terrain, cavern: Cavern) {
        for (y in cavern.blockOffsetY until cavern.blockOffsetY + cavern.cavernHeight) {
            val line = terrain[y]
            for (x in cavern.blockOffsetX..cavern.blockOffsetX + cavern.cavernWidth) {
                line[x] = null
            }
        }
    }

    private fun generateCavernEntry(terrain: Terrain, cavern: Cavern) {
        val entryX = cavern.blockOffsetX + random.nextInt(0, cavern.cavernWidth)
        for (y in cavern.blockOffsetY - 1 downTo 0) {
            val line = terrain[y]
            if (line[entryX] == null) {
                break
            }
            line[entryX] = null
        }
    }

    private fun generateCavernLoot(terrain: Terrain, cavern: Cavern) {
        val cavernFloorY = cavern.blockOffsetY + cavern.cavernHeight - 1
        val cavernFloorX = cavern.blockOffsetX + random.nextInt(0, cavern.cavernWidth)
        if (terrain[cavernFloorY + 1][cavernFloorX] != null) {
            terrain[cavernFloorY][cavernFloorX] = CHEST
        }
    }

    private fun build(xOffset: Int, yOffset: Int, terrain: Terrain): ArrayList<Entity> {
        val entities = arrayListOf<Entity>()
        terrain.forEachIndexed { y: Int, terrainLine: Array<EntityType?> ->
            terrainLine.forEachIndexed { x: Int, entityType: EntityType? ->
                if (entityType != null) {
                    val entity = Entity(
                        xOffset + BLOCK_SIZE * x,
                        yOffset + BLOCK_SIZE * y,
                        entityType
                    )
                    entities.add(entity)
                }
            }
        }
        return entities

    }
}