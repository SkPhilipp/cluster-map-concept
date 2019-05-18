package com.hileco.model

import com.hileco.interaction.ClusterMap
import java.util.concurrent.atomic.AtomicReference

data class Game(
    val entities: List<Entity>,
    val highlights: AtomicReference<List<Entity>>,
    val localPlayer: Entity,
    val clusterMap: ClusterMap<Entity>
)
