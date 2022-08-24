fun parseCardNumber(cardNumber: String): Long {
    // TODO
    val (A, B, C, D) = cardNumber.split(' ').map { it.toString() }
    val regex = "\\d\\d\\d\\d".toRegex()
    when {
        !regex.matches(A) -> throw Exception("First 4 symbols mus be digits!")
        !regex.matches(B) -> throw Exception("Second 4 symbols mus be digits!")
        !regex.matches(C) -> throw Exception("Third 4 symbols mus be digits!")
        !regex.matches(D) -> throw Exception("Fourth 4 symbols mus be digits!")
        else -> return (A + B + C + D).toLong()
    }
}