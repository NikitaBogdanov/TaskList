fun main() {
    // put your code here
    val input = readln().toCharArray()
    for (i in input.indices)
        if (input[i].isDigit()) {
            println(input[i])
            break
        }
}