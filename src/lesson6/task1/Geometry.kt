@file:Suppress("UNUSED_PARAMETER")

package lesson6.task1

import lesson1.task1.sqr


/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = Math.sqrt(sqr(x - other.x) + sqr(y - other.y))
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))

    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return Math.sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        return if (center.distance(other.center) <= radius + other.radius) 0.0
        else center.distance(other.center) - radius - other.radius
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = center.distance(p) <= radius
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
            other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
            begin.hashCode() + end.hashCode()
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    if (points.size == 1) throw IllegalArgumentException()
    var seg = Segment(Point(0.0, 0.0), Point(0.0, 0.0))
    for (i in 0..points.size - 1) {
        for (j in i + 1..points.size - 1) {
            if (points[i].distance(points[j]) > seg.begin.distance(seg.end))
                seg = Segment(points[i], points[j])
        }
    }
    return seg
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val radius = diameter.begin.distance(other = diameter.end) / 2
    val center = Point((diameter.begin.x + diameter.end.x) / 2, (diameter.begin.y + diameter.end.y) / 2)
    return Circle(center, radius)
}

/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line private constructor(val b: Double, val angle: Double) {
    init {
        assert(angle >= 0 && angle < Math.PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double) : this(point.y * Math.cos(angle) - point.x * Math.sin(angle), angle)

    /**
     * Средняя
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    fun crossPoint(other: Line): Point {
        val x = (other.b * Math.cos(angle) - b * Math.cos(other.angle)) / (Math.sin(angle) * Math.cos(other.angle) - Math.sin(other.angle) * Math.cos(angle))
        val y = (b * Math.sin(other.angle) - other.b * Math.sin(angle)) / (Math.cos(angle) * Math.sin(other.angle) - Math.cos(other.angle) * Math.sin(angle)) //(x * Math.sin(other.angle) + other.b) / Math.cos(other.angle)
        return Point(x, y)
    }

    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${Math.cos(angle)} * y = ${Math.sin(angle)} * x + $b)"
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val ang = Math.atan((s.end.y - s.begin.y) / (s.end.x - s.begin.x))
    return if (ang < 0) Line(s.begin, Math.PI + ang)
    else Line(s.begin, ang)
}

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line {
    return lineBySegment(Segment(a, b))
}

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val line = lineByPoints(a, b)
    val point = Point((a.x + b.x) / 2, (a.y + b.y) / 2)
    val ang = Math.PI / 2 + line.angle
    return if (ang >= Math.PI) Line(point, ang - Math.PI)
    else Line(point, ang)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size == 1) throw IllegalArgumentException()
    var circleOne = circles[0]
    var circleTwo = circles[1]
    for (i in 0..circles.size - 1) {
        for (j in i + 1..circles.size - 1) {
            if (circles[i].distance(circles[j]) < circleOne.distance(circleTwo)) {
                circleOne = circles[i]
                circleTwo = circles[j]
            }

        }
    }
    return Pair(circleOne, circleTwo)
}

/**
 * Сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    val bisectorCB = bisectorByPoints(c, b)
    val bisectorBA = bisectorByPoints(b, a)
    val center = bisectorCB.crossPoint(bisectorBA)
    val radius = center.distance(a)
    return Circle(center, radius)
}

/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */
fun containsAll(vararg points: Point, circle: Circle): Boolean {
    for (i in points) {
          if (!circle.contains(i)) return false
    }
    return true
}

fun minContainingCircle(vararg points: Point): Circle {
    var seg = Segment(Point(0.0, 0.0), Point(0.0, 0.0))
    var outSidePoints = mutableListOf<Point>()
    var count = 0

    for (i in 0..points.size - 1) {
        for (j in i + 1..points.size - 1) {
            if (points[i].distance(points[j]) > seg.begin.distance(seg.end))
                seg = Segment(points[i], points[j])
        }
    }
    var resultCircle = circleByDiameter(seg)
    for (i in points){
        if (!resultCircle.contains(i))
            outSidePoints.add(i)
    }
    if (outSidePoints.size == 0) return resultCircle
    for (i in outSidePoints){
        resultCircle = circleByThreePoints(seg.begin, seg.end, i)
        for (j in points) {
            if (resultCircle.contains(j)) count++
            else break
        }
        if (count == points.size) break
        else count = 0
    }
    return resultCircle
}

