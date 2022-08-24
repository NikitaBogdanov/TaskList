fun main() {
    // write your code here
//    val arraySize = readln().toInt()
//    val mas = IntArray(arraySize)
//    for (i in mas.indices) {
//        mas[i] = readln().toInt()
//    }

    val mas = IntArray(readln().toInt()) { readln().toInt() }
    var res = 0
    for (i in 0..(mas.lastIndex - 2))
        if (mas[i] + 1 == mas[i + 1] && mas[i + 1] + 1 == mas[i + 2]) ++res

    println(res)
}