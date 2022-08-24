fun main() {
    // write your code here
    println(
        when (readln().toInt()) {
            1 -> "move up"
            2 -> "move down"
            3 -> "move left"
            4 -> "move right"
            0 -> "do not move"
            else -> "error!"
        }
    )
}