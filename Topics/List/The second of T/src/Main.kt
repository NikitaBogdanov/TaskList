fun solution(names: List<String>): Int {
    // put your code here
    for (i in 1..names.size - 1 step 2)
        if (names[i].first() == 'T') return i
    return -1
}