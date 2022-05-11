package turtle

import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants

fun createPlayground(
    width: Int = 400, height: Int = width, code: Turtle.() -> Unit
) {
    EventQueue.invokeLater {
        val frame = JFrame("Turtle")
        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        val canvas = Canvas(width, height)
        frame.add(canvas)

        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true

        code(canvas.turtle)
        canvas.turtle.animate()
    }
}

class Canvas(
    width: Int, height: Int, private val shapes: MutableList<TurtleShape> = mutableListOf()
) : JPanel() {
    val turtle: Turtle

    init {
        isOpaque = true
        preferredSize = Dimension(width, height)
        turtle = Turtle(this, width / 2.0, height / 2.0)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        val g2d = g as Graphics2D
        shapes.forEach {
            g2d.color = it.color
            g2d.stroke = BasicStroke(it.width)
            g2d.draw(it.toShape())
        }

        g2d.color = Color.BLACK
        g2d.stroke = BasicStroke(1F)
        g2d.draw(turtle.toShape())
    }

    fun addShape(shape: TurtleShape) {
        shapes.add(shape)
        repaint()
    }

    fun reset() {
        shapes.clear()
        repaint()
    }
}