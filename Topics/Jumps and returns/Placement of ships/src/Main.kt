import java.util.*

fun main() {
    // put your code here
    val xx = mutableListOf<String>("1", "2", "3", "4", "5")
    val yy = mutableListOf<String>("1", "2", "3", "4", "5")
    val scanner = Scanner(System.`in`)
    while (scanner.hasNextLine()) {
        val (x, y) = scanner.nextLine().split(' ').map { it.toString() }
        xx.remove(x)
        yy.remove(y)
    }
    println(xx.joinToString(separator = " "))
    println(yy.joinToString(separator = " "))
}