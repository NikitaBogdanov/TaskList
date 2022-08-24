fun main() {
    // write your code here
    when (readln().toInt()) {
        2 -> println("Yes!")
        1, 3, 4 -> println("No!")
        else -> println("Unknown number")
    }
}