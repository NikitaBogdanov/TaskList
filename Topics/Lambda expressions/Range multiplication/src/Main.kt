val lambda: (Long, Long) -> Long = { a: Long, b: Long ->
    if (a == b) a else {
        var res = 1L
        for (i in a..b) res *= i
        res.toLong()
    }
}