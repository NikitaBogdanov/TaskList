fun main() {
    // write your code here
    val mas = IntArray(readln().toInt()) { readln().toInt() }
    val (P, M) = readln().split(' ').map { it.toInt() }

    if (mas.contains(P) && mas.contains(M)) println("YES") else println("NO")
}