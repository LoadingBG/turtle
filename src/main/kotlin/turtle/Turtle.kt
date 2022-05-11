package turtle

import java.awt.Color
import java.awt.Shape
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.geom.Path2D
import javax.swing.Timer
import kotlin.math.*
import kotlin.random.Random

private const val TURTLE_STEP_DELAY = 25
private const val TURTLE_ROTATION_DELAY = 10

/*
 * TODO:
 * TO SQUARE :LENGTH
 *   REPEAT [ FORWARD :LENGTH RIGHT 90 ]
 * END
 */

class Turtle(
    private val canvas: Canvas,
    private val xZero: Double,
    private val yZero: Double,
    private var x: Double,
    private var y: Double,
    private var rotationAngle: Int,
    private val actionQueue: ArrayDeque<Timer>,
    private var isPenDown: Boolean,
    private var penColor: Color,
    private var penWidth: Float,
) : TurtleShape(Color.BLACK, 1F) {
    constructor(canvas: Canvas, xZero: Double, yZero: Double) : this(
        canvas,
        xZero,
        yZero,
        xZero,
        yZero,
        0,
        ArrayDeque(),
        true,
        Color.BLACK,
        1F
    )

    override fun toShape(): Shape {
        val radians = Math.toRadians(rotationAngle.toDouble())
        val sine = sin(radians)
        val cosine = cos(radians)

        val shape = Path2D.Double()
        shape.moveTo((x - 20 * cosine), (y - 20 * sine))
        shape.lineTo((x + 20 * sine), (y - 20 * cosine))
        shape.lineTo((x + 20 * cosine), (y + 20 * sine))
        shape.closePath()
        return shape
    }

    fun animate() {
        if (actionQueue.isNotEmpty()) {
            actionQueue.removeFirst().start()
        }
    }

    fun forward(steps: Number) {
        val wholePart = steps.toInt()
        val wholePartAbs = abs(wholePart)
        val decimalPart = steps.toDouble() - wholePart
        val stepsSign = sign(steps.toDouble())

        lateinit var timer: Timer

        val animation = object : ActionListener {
            private var frame = 1
            override fun actionPerformed(e: ActionEvent?) {
                val radians = Math.toRadians(rotationAngle.toDouble())
                val deltaX = stepsSign * sin(radians)
                val deltaY = stepsSign * cos(radians)

                if (isPenDown) {
                    canvas.addShape(TurtleLine(x, y, x + deltaX, y - deltaY, penColor, penWidth))
                }

                x += deltaX
                y -= deltaY

                canvas.repaint()

                if (frame == wholePartAbs) {
                    timer.stop()
                    x += stepsSign * decimalPart * sin(radians)
                    y -= stepsSign * decimalPart * cos(radians)
                    println("Turtle at ($x, $y)")
                    animate()
                } else {
                    ++frame
                }
            }
        }

        timer = Timer(TURTLE_STEP_DELAY, animation)
        actionQueue.addLast(timer)
    }

    fun backward(steps: Double) = forward(-steps)

    fun right(angle: Int) {
        val angleSign = sign(angle.toDouble()).toInt()
        val angleAbs = abs(angle)

        lateinit var timer: Timer

        val animation = object : ActionListener {
            private var frame = 1
            override fun actionPerformed(e: ActionEvent) {
                rotationAngle += angleSign
                canvas.repaint()

                if (frame == angleAbs) {
                    timer.stop()
                    animate()
                } else {
                    ++frame
                }
            }
        }

        timer = Timer(TURTLE_ROTATION_DELAY, animation)
        actionQueue.addLast(timer)
    }

    fun left(angle: Int) = right(-angle)

    fun clearScreen() {
        val timer = Timer(0) {
            x = xZero
            y = yZero
            rotationAngle = 0
            canvas.reset()
        }
        timer.isRepeats = false
        actionQueue.addLast(timer)
    }

    fun penUp() {
        actionQueue.addLast(Timer(0) {
            isPenDown = false
        })
    }

    fun penDown() {
        actionQueue.addLast(Timer(0) {
            isPenDown = true
        })
    }

    fun repeat(times: Int, action: (Int) -> Unit) {
        1.rangeTo(times).forEach(action)
    }

    fun sqrt(number: Number): Double {
        return kotlin.math.sqrt(number.toDouble())
    }

    fun random(number: Int): Int {
        return Random.nextInt(number)
    }

    fun <T> pick(vararg list: T): T {
        return list[Random.nextInt(list.size)]
    }
}