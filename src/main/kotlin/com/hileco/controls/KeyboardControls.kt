package com.hileco.controls

import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.awt.event.KeyListener

class KeyboardControls(private val controlsListener: ControlsListener) : KeyListener {
    override fun keyTyped(e: KeyEvent?) {
    }

    override fun keyPressed(e: KeyEvent?) {
        if (e != null) {
            when (e.keyCode) {
                VK_UP -> controlsListener.startMoveUp()
                VK_DOWN -> controlsListener.startMoveDown()
                VK_RIGHT -> controlsListener.startMoveRight()
                VK_LEFT -> controlsListener.startMoveLeft()
            }
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        if (e != null) {
            when (e.keyCode) {
                VK_UP -> controlsListener.stopMoveUp()
                VK_DOWN -> controlsListener.stopMoveDown()
                VK_RIGHT -> controlsListener.stopMoveRight()
                VK_LEFT -> controlsListener.stopMoveLeft()
            }
        }
    }
}