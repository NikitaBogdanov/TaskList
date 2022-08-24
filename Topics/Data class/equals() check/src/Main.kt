data class Client(val name: String, val age: Int, val balance: Int) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false

        other as Client
        if (name == other.name && age == other.age) return true
        return false
    }
}

fun main() {
    // implement your code here
    val client1 = Client(
        name = readln(),
        age = readln().toInt(),
        balance = readln().toInt()
    )

    val client2 = Client(
        name = readln(),
        age = readln().toInt(),
        balance = readln().toInt()
    )

//    println(client1.equals(client2))
    println(client1 == client2)
}