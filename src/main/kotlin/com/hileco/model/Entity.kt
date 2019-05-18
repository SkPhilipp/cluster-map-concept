package com.hileco.model

import com.hileco.interaction.Clusterable

data class Entity(
    var x: Int,
    var y: Int,
    val entityType: EntityType
) : Clusterable {
    override fun clusterKey(): Pair<Int, Int> {
        return x to y
    }
}
