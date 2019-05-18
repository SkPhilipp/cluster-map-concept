package com.hileco.interaction

data class Area(val x1: Int, val y1: Int, val x2: Int, val y2: Int)

interface Clusterable {
    fun area(): Area
}
