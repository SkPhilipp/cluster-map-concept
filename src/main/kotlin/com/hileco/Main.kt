package com.hileco

import com.hileco.controls.ControlsListener
import com.hileco.generation.TerrainGenerator
import com.hileco.interaction.ClusterMap
import com.hileco.model.Entity
import com.hileco.model.EntityTypes.Companion.BLOCK_SIZE
import com.hileco.model.EntityTypes.Companion.PLAYER
import com.hileco.model.Game
import java.util.concurrent.atomic.AtomicReference

fun main() {
    val terrainGenerator = TerrainGenerator(0)
    val entities = arrayListOf<Entity>()
    val localPlayer = Entity(BLOCK_SIZE * 6, 100 - PLAYER.height, PLAYER)
    entities.addAll(terrainGenerator.generate(0, 100, 100, 100))
    entities.add(localPlayer)
    val clusterMap = ClusterMap<Entity>(BLOCK_SIZE * 6, BLOCK_SIZE * 6)
    entities.forEach {
        clusterMap.add(it)
    }
    val highlights = AtomicReference<List<Entity>>()
    val game = Game(entities, highlights, localPlayer, clusterMap)
    val controlsListener = ControlsListener(localPlayer)
    Thread(Runnable {
        while (true) {
            Thread.sleep(10)
            clusterMap.update(localPlayer) {
                controlsListener.apply()
            }
            val clusterOf = clusterMap.clusterOf(localPlayer)
            highlights.set(clusterOf)
        }
    }).start()
    Pane(game, controlsListener)
}
