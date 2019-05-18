package com.hileco.interaction

import com.hileco.model.Entity
import com.hileco.model.EntityType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class CollisionsTest {
    /**
     * a
     *  b
     */
    @Test
    fun aInBLeft() {
        val a = Entity(0, 0, EntityType(10, 10))
        val b = Entity(5, 5, EntityType(10, 10))
        val collisions = Collisions()
        Assertions.assertTrue(collisions.areColliding(a, b))
        Assertions.assertTrue(collisions.areColliding(b, a))
    }

    /**
     *  a
     * b
     */
    @Test
    fun aInBRight() {
        val a = Entity(5, 0, EntityType(10, 10))
        val b = Entity(0, 5, EntityType(10, 10))
        val collisions = Collisions()
        Assertions.assertTrue(collisions.areColliding(a, b))
        Assertions.assertTrue(collisions.areColliding(b, a))
    }

    /**
     * a b
     */
    @Test
    fun aLeftOfB() {
        val a = Entity(0, 0, EntityType(10, 10))
        val b = Entity(15, 0, EntityType(10, 10))
        val collisions = Collisions()
        Assertions.assertFalse(collisions.areColliding(a, b))
        Assertions.assertFalse(collisions.areColliding(b, a))
    }

    /**
     * a
     *
     * b
     */
    @Test
    fun aBelowB() {
        val a = Entity(0, 15, EntityType(10, 10))
        val b = Entity(0, 0, EntityType(10, 10))
        val collisions = Collisions()
        Assertions.assertFalse(collisions.areColliding(a, b))
        Assertions.assertFalse(collisions.areColliding(b, a))
    }
}