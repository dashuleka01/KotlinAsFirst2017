@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var result = 0
    var num = abs(n)
    do {
        result++
        num /= 10
    } while (num > 0)
    return result
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var x = 1
    var y = 0
    var z = x + y
    for (i in 2..n) {
        z = x + y
        y = x
        x = z
    }
    return z
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    return m * n / ncd(m, n)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n / 2) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n / 2 downTo 2) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun ncd(m: Int, n: Int): Int {
    var nNum = n
    var mNum = m
    while (nNum != 0 && mNum != 0) {
        if (mNum > nNum) mNum = mNum % nNum
        else nNum = nNum % mNum
    }
    return nNum + mNum
}

fun isCoPrime(m: Int, n: Int): Boolean {
    return ncd(m, n) == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = ceil(sqrt(m.toDouble())) <= floor(sqrt(n.toDouble()))

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */

fun sin(x: Double, eps: Double): Double {
    val x1 = x % (2 * PI)
    var i = 1
    var sin = 0.0
    var sign = 0.0
    var a = (pow(x1, i.toDouble()) / factorial(i)) * pow(-1.0, sign)
    while (abs(a) >= eps) {
        sin += a
        i += 2
        sign++
        a = (pow(x1, i.toDouble()) / factorial(i)) * pow(-1.0, sign)
    }
    return sin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var cos = 1.0
    var flag = -1
    var i = 2
    val x1 = x % (2 * PI)
    var a = pow(x1, i.toDouble()) / factorial(i) * flag
    while (abs(a) >= eps) {
        cos += a
        flag = -flag
        i += 2
        a = pow(x1, i.toDouble()) / factorial(i) * flag
    }
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var digitCount = digitNumber(n)
    var num = n
    var result = 0
    for (i in 1..digitCount) {
        result += pow(10.0, digitCount.toDouble() - i).toInt() * (num % 10)
        num /= 10
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    val revertNum = revert(n)
    return n == revertNum
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var firstNum = n % 10
    var num = n / 10
    while (num > 0) {
        if (num % 10 != firstNum) return true
        num /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var count = 0
    var num = 0
    var i = 1
    while (count < n) {
        num = i * i
        count += digitNumber(i * i)
        i++
    }
    return (num / pow(10.0, count - n * 1.0).toInt()) % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var x = 1
    var y = 0
    var z = x + y
    var count = 1
    while (count < n) {
        z = x + y
        y = x
        x = z
        count += digitNumber(z)
    }
    return (z / pow(10.0, count - n * 1.0).toInt()) % 10
}