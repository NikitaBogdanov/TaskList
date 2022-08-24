fun main() {
    // write your code here
    val (firstNumber, operator, secondNumber) = readln().split(' ').map { it.toString() }
    when (operator) {
        "+" -> println(firstNumber.toLong() + secondNumber.toLong())
        "-" -> println(firstNumber.toLong() - secondNumber.toLong())
        "*" -> println(firstNumber.toLong() * secondNumber.toLong())
        "/" -> {
            if (secondNumber == "0") println("Division by 0!") else println(firstNumber.toLong() / secondNumber.toLong())
        }
        else -> println("Unknown operator")
    }
}