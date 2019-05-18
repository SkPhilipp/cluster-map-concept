package com.hileco

import com.hileco.controls.ActionListener
import com.hileco.controls.ControlsListener
import com.hileco.drawing.GraphicsEngine
import com.hileco.drawing.Pane
import com.hileco.generation.TerrainGenerator
import com.hileco.interaction.ClusterMap
import com.hileco.model.Entity
import com.hileco.model.EntityTypes.Companion.BLOCK_SIZE
import com.hileco.model.EntityTypes.Companion.PLAYER
import com.hileco.model.Game

fun main() {
    val terrainGenerator = TerrainGenerator(0)
    val entities = arrayListOf<Entity>()
    val localPlayer = Entity(BLOCK_SIZE * 6, 100 - PLAYER.height, PLAYER)
    entities.addAll(terrainGenerator.generate(0, 100, 100, 100))
    entities.add(localPlayer)
    val clusterMap = ClusterMap<Entity>(BLOCK_SIZE * 2, BLOCK_SIZE * 2)
    entities.forEach {
        clusterMap.add(it)
    }
    val game = Game(entities, localPlayer, clusterMap)
    val actionListener = ActionListener(game, localPlayer)
    val controlsListener = ControlsListener(actionListener)
    Thread(Runnable {
        while (true) {
            Thread.sleep(10)
            clusterMap.update(localPlayer) {
                controlsListener.apply()
            }
        }
    }).start()
    val graphicsEngine = GraphicsEngine(game)
    Pane(graphicsEngine, controlsListener)
}
