package turtle

fun main() {
    createPlayground {
//        fun Turtle.rightTriangle(length: Number) {
//            forward(length)
//            right(135)
//            forward(length.toDouble() * sqrt(2))
//            right(135)
//            forward(length)
//            right(90)
//        }
//
//        fun Turtle.pyramid() {
//            right(45)
//            repeat(4) {
//                repeat(10) { repcount ->
//                    rightTriangle(repcount * 10)
//                }
//                right(90)
//            }
//            left(45)
//        }
//
//        pyramid()

//        fun Turtle.polygon(sides: Int, length: Number) {
//            repeat(sides) {
//                forward(length)
//                right(360 / sides)
//            }
//        }
//
//        fun Turtle.hexagonFlower(petals: Int) {
//            repeat(petals) {
//                polygon(5, 50)
//                right(360 / petals)
//            }
//        }
//
//        hexagonFlower(10)

//        fun Turtle.downSlant(length: Number) {
//            repeat(2) {
//                forward(length)
//                right(135)
//                forward(20)
//                right(45)
//            }
//            right(135)
//            forward(20)
//            left(135)
//        }
//
//        fun Turtle.upSlant(length: Number) {
//            repeat(2) {
//                forward(length)
//                right(45)
//                forward(20)
//                right(135)
//            }
//            right(45)
//            forward(20)
//            left(45)
//        }
//
//        fun Turtle.sheet() {
//            repeat(2) {
//                downSlant(100)
//            }
//            repeat(2) {
//                upSlant(100)
//            }
//            downSlant(100)
//            repeat(2) {
//                upSlant(50)
//            }
//            downSlant(50)
//            repeat(2) {
//                downSlant(10)
//            }
//            upSlant(10)
//        }
//
//        sheet()

//        repeat(1000) {
//            forward(10)
//            right(random(360))
//        }

//        fun Turtle.star(points: Int) {
//            repeat(points) {
//                forward(100)
//                right(180 - (180 / points))
//            }
//        }
//
//        star(pick(5, 7, 11))

        fun Turtle.square() {
            repeat(4) {
                forward(50)
                right(90)
            }
        }

        fun Turtle.setPen(brightness: Int) {
            setPenColor(255, 255 - brightness, brightness)
        }

        fun Turtle.squareFlower() {
            repeat(64) {
                setPen(it * 4 - 1)
                right(360 / 64)
                square()
            }
        }

        squareFlower()
    }
}