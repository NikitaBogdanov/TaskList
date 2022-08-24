fun solution(strings: List<String>, str: String): Int {
    // put your code here
    var res = 0
    for (ouch in strings)
        if (ouch == str) ++res
    return res
}