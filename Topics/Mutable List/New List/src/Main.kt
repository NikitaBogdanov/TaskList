fun solution(numbers: List<Int>, number: Int): MutableList<Int> {
    // put your code here
    val k = numbers.toMutableList()
    k.add(number)
    return k
}