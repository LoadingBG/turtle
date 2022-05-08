package turtle

import java.awt.Color
import java.awt.Shape
import java.awt.geom.Line2D

sealed class TurtleShape(val color: Color, val width: Float) {
    abstract fun toShape(): Shape
}

class TurtleLine(
    private val x1: Double,
    private val y1: Double,
    private val x2: Double,
    private val y2: Double,
    color: Color,
    width: Float
) : TurtleShape(color, width) {
    override fun toShape(): Shape {
        return Line2D.Double(x1, y1, x2, y2)
    }
}