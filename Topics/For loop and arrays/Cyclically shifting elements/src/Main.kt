fun main() {
    // write your code here
    val mas = IntArray(readln().toInt()) { readln().toInt() }

    var buf = 0
    buf = mas.last()
    for (i in mas.lastIndex downTo 1) mas[i] = mas[i - 1]
    mas[0] = buf

    println(mas.joinToString(separator = " "))
}