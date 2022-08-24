enum class Countries(val currency: String) {
    Germany("Euro"),
    Mali("CFA franc"),
    Dominica("Eastern Caribbean dollar"),
    Canada("Canadian dollar"),
    Spain("Euro"),
    Australia("Australian dollar"),
    Brazil("Brazilian real"),
    Senegal("CFA franc"),
    France("Euro"),
    Grenada("Eastern Caribbean dollar"),
    Kiribati("Australian dollar")
}

fun checkByName(countryName: String): Boolean {
    for (enum in Countries.values()) {
        if (enum.name == countryName) return true
    }
    return false
}

fun main() {
    // put your code here
    val (country1, country2) = readln().split(' ')
    if (!checkByName(country1) || !checkByName(country2)) println("false")
    else if (Countries.valueOf(country1).currency == Countries.valueOf(country2).currency) println("true")
    else println("false")
}