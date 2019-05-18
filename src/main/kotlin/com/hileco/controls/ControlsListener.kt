package com.hileco.controls

import com.hileco.model.Entity

class Control {
    var current = 0
    var stop = true
}

class ControlsListener(private val controlledEntity: Entity) {
    private var left = Control()
    private var right = Control()
    private var up = Control()
    private var down = Control()

    fun startMoveRight() {
        right.current = 1
        right.stop = false
    }

    fun startMoveLeft() {
        left.current = 1
        left.stop = false
    }

    fun startMoveUp() {
        up.current = 1
        up.stop = false
    }

    fun startMoveDown() {
        down.current = 1
        down.stop = false
    }

    fun stopMoveRight() {
        right.stop = true
    }

    fun stopMoveLeft() {
        left.stop = true
    }

    fun stopMoveUp() {
        up.stop = true
    }

    fun stopMoveDown() {
        down.stop = true
    }

    fun apply() {
        val horizontal = -left.current + right.current
        val vertical = -up.current + down.current
        controlledEntity.x += horizontal
        controlledEntity.y += vertical
        if (left.stop) {
            left.current = 0
        }
        if (right.stop) {
            right.current = 0
        }
        if (up.stop) {
            up.current = 0
        }
        if (down.stop) {
            down.current = 0
        }
    }
}
