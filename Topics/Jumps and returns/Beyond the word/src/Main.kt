fun main() {
    // put your code here
//    var alphabet = "abcdefghijklmnopqrstuvwxyz"
//    val input = readln()
//    for (i in input.indices) {
//        if (input[i].isUpperCase()) continue
//        else if (alphabet.contains(input[i])) alphabet = alphabet.replace(input[i].toString(),"")
//        else continue
//    }
//    println(alphabet)

    val input = readln()
    for (i in 'a'..'z')
        if (input.contains(i)) continue else print(i)
}