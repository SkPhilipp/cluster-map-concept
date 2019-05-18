package com.hileco.interaction

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

    private fun keyOf(clusterable: Clusterable): Pair<Int, Int> {
        val (x, y) = clusterable.clusterKey()
        val clusterX = (x - x % clusterWidth) / clusterWidth
        val clusterY = (y - y % clusterHeight) / clusterHeight
        return clusterX to clusterY
    }

    /**
     * Adds the given [clusterable] to the map.
     */
    fun add(clusterable: V) {
        val key = keyOf(clusterable)
        val cluster = map.getOrPut(key, { arrayListOf() })
        cluster.add(clusterable)
    }

    /**
     * Retrieves the cluster of the given [clusterable].
     */
    fun clusterOf(clusterable: V): List<V> {
        val key = keyOf(clusterable)
        return map.getOrElse(key, {
            arrayListOf()
        })
    }

    /**
     * Removes the given [clusterable] from the map.
     */
    fun remove(clusterable: V) {
        val key = keyOf(clusterable)
        map[key]?.remove(clusterable)
    }

    /**
     * Removes the given [clusterable] from the map.
     */
    fun update(clusterable: V, updater: (V) -> Unit) {
        val previousKey = keyOf(clusterable)
        updater(clusterable)
        val updatedKey = keyOf(clusterable)
        if (previousKey != updatedKey) {
            remove(clusterable)
            add(clusterable)
        }
    }
}