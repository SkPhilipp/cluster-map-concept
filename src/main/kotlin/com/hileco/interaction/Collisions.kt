package com.hileco.interaction

import com.hileco.model.Entity

class Collisions {
    fun areColliding(a: Entity, b: Entity): Boolean {
        return !(a.x > b.x + b.entityType.width - 1
                || b.x > a.x + a.entityType.width - 1
                || a.y > b.y + b.entityType.height - 1
                || b.y > a.y + a.entityType.height - 1)
    }
}