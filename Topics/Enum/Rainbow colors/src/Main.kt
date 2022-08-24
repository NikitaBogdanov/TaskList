enum class rainbow { red, orange, yellow, green, blue, indigo, violet }

fun main() {
    try {
        val i = rainbow.valueOf(readln())
        println(true)
    } catch (e: IllegalArgumentException) {
        println(false)
    }

}