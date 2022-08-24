fun main() {
    val input = readLine()!!
    // write code here
    when {
        input.isEmpty() -> println(input)
        input.first() == 'i' -> println(input.drop(1).toInt() + 1)
        input.first() == 's' -> println(input.drop(1).reversed())
        else -> println(input)
    }
}