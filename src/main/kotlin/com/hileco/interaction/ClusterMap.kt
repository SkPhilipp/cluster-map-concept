package com.hileco.interaction

import java.util.*
import kotlin.collections.HashMap


class ClusterMap<V : Clusterable>(
    /**
     * Width of a single cluster, the [ClusterMap] itself has no explicit limit.
     */
    private val clusterWidth: Int,
    /**
     * Height of a single cluster, the [ClusterMap] itself has no explicit limit.
     */
    private val clusterHeight: Int
) {
    private val map: MutableMap<Pair<Int, Int>, MutableList<V>> = HashMap()

    private fun keysOf(clusterable: Clusterable): Pair<IntRange, IntRange> {
        val (x1, y1, x2, y2) = clusterable.area()
        val clusterX1 = (x1 - x1 % clusterWidth) / clusterWidth
        val clusterY1 = (y1 - y1 % clusterHeight) / clusterHeight
        val clusterX2 = (x2 - x2 % clusterWidth) / clusterWidth
        val clusterY2 = (y2 - y2 % clusterHeight) / clusterHeight
        return IntRange(clusterX1, clusterX2) to IntRange(clusterY1, clusterY2)
    }

    private fun keyOf(x: Int, y: Int): Pair<Int, Int> {
        val clusterX = (x - x % clusterWidth) / clusterWidth
        val clusterY = (y - y % clusterHeight) / clusterHeight
        return clusterX to clusterY
    }

    /**
     * Adds the given [clusterable] to the map.
     */
    fun add(clusterable: V) {
        val keys = keysOf(clusterable)
        keys.first.forEach { x ->
            keys.second.forEach { y ->
                val cluster = map.getOrPut(x to y, { arrayListOf() })
                cluster.add(clusterable)
            }
        }
    }

    /**
     * Retrieves the entries in the clusters of the given [clusterable].
     *
     * May contain the same entries multiple times.
     */
    fun clusterOf(clusterable: V): List<V> {
        val keys = keysOf(clusterable)
        return keys.first.flatMap { x ->
            keys.second.flatMap { y ->
                map.getOrDefault(x to y, Collections.emptyList())
            }
        }
    }

    /**
     * Retrieves the entries in the clusters of the given location.
     *
     * May contain the same entries multiple times.
     */
    fun clusterOf(x: Int, y: Int): List<V> {
        val key = keyOf(x, y)
        return map.getOrDefault(key, Collections.emptyList())
    }

    /**
     * Removes the given [clusterable] from the map.
     */
    fun remove(clusterable: V) {
        val keys = keysOf(clusterable)
        keys.first.forEach { x ->
            keys.second.forEach { y ->
                map[x to y]?.remove(clusterable)
            }
        }
    }

    /**
     * Removes the given [clusterable] from the map.
     */
    fun update(clusterable: V, updater: (V) -> Unit) {
        val previousKey = keysOf(clusterable)
        updater(clusterable)
        val updatedKey = keysOf(clusterable)
        if (previousKey != updatedKey) {
            remove(clusterable)
            add(clusterable)
        }
    }
}