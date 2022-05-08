package turtle

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.Line2D
import kotlin.math.cos
import kotlin.math.sin

class Turtle(
    private val graphics: Graphics2D,
    private val xZero: Double,
    private val yZero: Double,
    private var x: Double,
    private var y: Double,
    private var angle: Double,
    private var isPenDown: Boolean,
    private var penColor: Color,
    private var penWidth: Float,
) {
    constructor(graphics: Graphics2D, xZero: Double, yZero: Double) : this(
        graphics,
        xZero,
        yZero,
        xZero,
        yZero,
        0.0,
        true,
        Color.BLACK,
        1F
    )

    fun forward(steps: Int) {
        val deltaX = steps * sin(angle)
        val deltaY = steps * cos(angle)

        if (isPenDown) {
            graphics.color = penColor
            graphics.stroke = BasicStroke(penWidth)
            graphics.draw(Line2D.Double(x, y, x + deltaX, y - deltaY))
        }

        x += deltaX
        y -= deltaY
    }

    fun right(angle: Int) {
        this.angle += angle
    }
}