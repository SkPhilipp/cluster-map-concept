package com.hileco.model

import com.hileco.interaction.Area
import com.hileco.interaction.Clusterable

data class Entity(
    var x: Int,
    var y: Int,
    val entityType: EntityType
) : Clusterable {
    override fun area(): Area {
        return Area(x, y, x + entityType.width, y + entityType.height)
    }
}
