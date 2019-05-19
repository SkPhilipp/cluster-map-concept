package com.hileco.interaction

import com.hileco.model.Entity

class Collisions {
    fun isOn(x: Int, y: Int, a: Entity): Boolean {
        return !(a.x > x
                || x > a.x + a.type.width - 1
                || a.y > y
                || y > a.y + a.type.height - 1)
    }

    fun distanceBetween(a: Entity, b: Entity): Double {
        return Math.sqrt(((b.y - a.y) * (b.y - a.y) + (b.x - a.x) * (b.x - a.x)).toDouble())
    }

    fun areColliding(a: Entity, b: Entity): Boolean {
        return !(a.x > b.x + b.type.width - 1
                || b.x > a.x + a.type.width - 1
                || a.y > b.y + b.type.height - 1
                || b.y > a.y + a.type.height - 1)
    }

    fun wouldCollide(
        a: Entity,
        aXOffset: Int,
        aYOffset: Int,
        b: Entity
    ): Boolean {
        return !(a.x + aXOffset > b.x + b.type.width - 1
                || b.x > a.x + aXOffset + a.type.width - 1
                || a.y + aYOffset > b.y + b.type.height - 1
                || b.y > a.y + aYOffset + a.type.height - 1)
    }
}