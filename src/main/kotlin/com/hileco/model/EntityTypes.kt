package com.hileco.model

import java.awt.Color

class EntityTypes {
    companion object {
        const val BLOCK_SIZE = 12
        val BLOCK_BROWN = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(90, 35, 35))
        val BLOCK_GREEN = EntityType(BLOCK_SIZE, BLOCK_SIZE, Color(0, 140, 0))
        val PLAYER = EntityType(BLOCK_SIZE, BLOCK_SIZE * 2, Color(255, 0, 0))
    }
}