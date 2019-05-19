package com.hileco.controls

import com.hileco.interaction.InteractionListener
import java.awt.event.*

class MouseControls(private val interactionListener: InteractionListener) :
    MouseListener, MouseMotionListener, MouseWheelListener {

    override fun mouseReleased(e: MouseEvent?) {
    }

    override fun mouseEntered(e: MouseEvent?) {
    }

    override fun mouseClicked(e: MouseEvent?) {
    }

    override fun mouseExited(e: MouseEvent?) {
    }

    override fun mousePressed(e: MouseEvent?) {
        if (e != null) {
            interactionListener.trigger(e.x, e.y)
        }
    }

    override fun mouseMoved(e: MouseEvent?) {
        if (e != null) {
            interactionListener.target(e.x, e.y)
        }
    }

    override fun mouseDragged(e: MouseEvent?) {
    }

    override fun mouseWheelMoved(e: MouseWheelEvent?) {
    }
}
