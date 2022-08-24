fun main() {
    val line1 = readln()!!.toInt()
    val line2 = readln()!!.toInt()

    val product = line1 * line2
    println(if (product == 0) "It can't be zero!" else product)
}