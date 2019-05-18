package com.hileco.model

import com.hileco.interaction.ClusterMap

data class Game(
    val entities: List<Entity>,
    val localPlayer: Entity,
    val clusterMap: ClusterMap<Entity>
)
