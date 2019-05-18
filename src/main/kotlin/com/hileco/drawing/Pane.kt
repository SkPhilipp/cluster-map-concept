package com.hileco.drawing

import com.hileco.controls.ControlsListener
import com.hileco.controls.KeyboardControls
import java.awt.Frame
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.Timer


class Pane(
    private val graphicsEngine: GraphicsEngine,
    controlsListener: ControlsListener,
    private val pixelWidth: Int = 400,
    private val pixelHeight: Int = 400
) : Frame("Concept") {

    init {
        setSize(pixelWidth, pixelHeight)
        isVisible = true
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                System.exit(0)
            }
        })
        addKeyListener(KeyboardControls(controlsListener))
        val timer = Timer(20, ActionListener { redraw() })
        timer.start()
        createBufferStrategy(2)
    }

    private fun redraw() {
        do {
            val bufferGraphics = bufferStrategy.drawGraphics
            bufferGraphics.clearRect(0, 0, pixelWidth, pixelHeight)
            graphicsEngine.draw(bufferGraphics)
            bufferGraphics.dispose()
            bufferStrategy.show()
        } while (bufferStrategy.contentsLost())
    }
}
