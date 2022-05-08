package turtle

import java.awt.Color
import kotlin.math.cos
import kotlin.math.sin

class Turtle(
    private val canvas: Canvas,
    private val xZero: Double,
    private val yZero: Double,
    private var x: Double,
    private var y: Double,
    private var angle: Double,
    private var isPenDown: Boolean,
    private var penColor: Color,
    private var penWidth: Float,
) {
    constructor(canvas: Canvas, xZero: Double, yZero: Double) : this(
        canvas,
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
            canvas.addShape(TurtleLine(x, y, x + deltaX, y - deltaY, penColor, penWidth))
        }

        x += deltaX
        y -= deltaY
    }

    fun backward(steps: Int) = forward(-steps)

    fun right(angle: Int) {
        this.angle += Math.toRadians(angle.toDouble())
    }

    fun left(angle: Int) = right(-angle)

    fun clearScreen() {
        canvas.reset()
        x = xZero
        y = yZero
    }
}