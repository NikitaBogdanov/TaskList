fun main() {    
    val numbers = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
    // Do not touch lines above
    // Write only exchange actions here.
    val buf = numbers.first()
    numbers[0] = numbers.last()
    numbers[numbers.lastIndex] = buf
    // Do not touch lines below
    // numbers[0] = numbers[numbers.lastIndex].also { numbers[numbers.lastIndex] = numbers[0] }
    println(numbers.joinToString(separator = " "))
}