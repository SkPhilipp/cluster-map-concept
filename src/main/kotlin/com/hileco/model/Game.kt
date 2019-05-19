package com.hileco.model

import com.hileco.interaction.ClusterMap

data class Game(
    val entities: MutableList<Entity>,
    val localPlayer: Entity,
    val clusterMap: ClusterMap<Entity>,
    var targetLocation: Pair<Int, Int>? = null
)
