package com.hileco.interaction

import com.hileco.model.Entity

class Collisions {
    fun areColliding(a: Entity, b: Entity): Boolean {
        if (a.x > b.x + b.entityType.width || b.x > a.x + a.entityType.width) {
            return false
        }
        if (a.y > b.y + b.entityType.height || b.y > a.y + a.entityType.height) {
            return false
        }
        return true
    }
}