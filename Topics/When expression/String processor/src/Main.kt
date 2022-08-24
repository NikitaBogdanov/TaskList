fun main() {
    // write your code here
    val s1 = readln()
    val opetarion = readln()
    val s2 = readln()
    when (opetarion) {
        "equals" -> println(s1 == s2)
        "plus" -> println(s1 + s2)
        "endsWith" -> println(s1.endsWith(s2))
        else -> println("Unknown operation")
    }
}