// TODO: provide three functions here
fun identity(x: Int): Int = x

fun half(x: Int): Int = x / 2

fun zero(x: Int): Int = 0

fun generate(functionName: String): (Int) -> Int {
    // TODO: provide implementation here
    return when (functionName) {
        "identity" -> ::identity
        "half" -> ::half
        else -> ::zero
    }
}