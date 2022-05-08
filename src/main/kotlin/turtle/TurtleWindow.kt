package turtle

import java.awt.Canvas
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.WindowConstants

fun createPlayground(width: Int = 400, height: Int = width, xZero: Int = width / 2, yZero: Int = height / 2, code: Turtle.() -> Unit) {
    val frame = JFrame("Turtle")
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.contentPane.preferredSize = Dimension(width, height)
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.layout = GridLayout(1, 1)

    val canvas = Canvas()
    frame.add(canvas)

    frame.isVisible = true

    canvas.graphics.color = Color.BLACK
    canvas.graphics.fillRect(0, 0, canvas.width, canvas.height)
    println("Canvas ${canvas.width} x ${canvas.height}")

    code(Turtle(canvas.graphics as Graphics2D, xZero.toDouble(), yZero.toDouble()))
}