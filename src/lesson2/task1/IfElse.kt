@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
   /* var word = ""
    if (age in 1..4 || ((age % 100) >= 21 && (age % 10) in 1..4) || (age in 101..104)) {
        if (age == 1 || (age % 10) == 1) {
            word = "$age год"
        }
        else
            word = "$age года"
    }
    else{
        word = "$age лет"
    }
    return*/

     return if (age in 1..4 || ((age % 100) >= 21 && (age % 10) in 1..4) || (age in 101..104)) {
        if (age == 1 || (age % 10) == 1)
            "$age год"
        else
            "$age года"
    }
    else {
        "$age лет"
    }
}





/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    var s = v1 * t1 + v2 * t2 + v3 * t3
    var s1 = v1 * t1
    var s2 = v2 * t2
    var s3 = v3 * t3

    return when{
        s / 2.0 <= s1 -> (s / 2.0) / v1
        s / 2.0 in s1..(s1 + s2) -> t1 + (s / 2.0 - s1) / v2
        else -> t2 + t1 + (s / 2.0 - s1 - s2) / v3

    }
}
/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val rook1Attack = kingX == rookX1 || kingY == rookY1
    val rook2Attack = kingX == rookX2 || kingY == rookY2
    return when{
        rook1Attack && rook2Attack -> 3
        rook1Attack -> 1
        rook2Attack -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val rookAttack = kingX == rookX || kingY == rookY
    val bishopAttack = abs(kingX - bishopX) == abs(kingY - bishopY)
    return when{
        rookAttack && bishopAttack -> 3
        rookAttack -> 1
        bishopAttack -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var MaxNum = max(a, b)
    MaxNum = max(MaxNum, c)
    var MinNum = min(a, b)
    MinNum = min(MinNum, c)
    val MiddleNum = a + b + c - MinNum - MaxNum
    val result = MinNum * MinNum + MiddleNum * MiddleNum - MaxNum * MaxNum

    return when{
        a + b <= c || b + c <= a || c + a <= b -> -1
        result < 0 -> 2
        result > 0 -> 0
        result == 0.0 -> 1
        else -> -2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when{
        a <= c && b in c..d -> b - c //пересечение, слева AB, справа CD,  A-C-B-D
        c >= a && d in c..b -> d - c //CD в AB, A-C-D-B
        a >= c && b in a..d -> b - a //AB в CD, С-A-B-D
        a >= c && d in a..b -> d - a //пересечение, слева CD, справа AB, C-A-D-B
        else -> -1
    }
}
