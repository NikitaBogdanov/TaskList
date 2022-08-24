data class CustomerInfo(
    /** Unique user id */
    val uid: String,
    /** Operation system: Windows, Linux, macOS, iOS */
    val operationSystem: String,
    /** In GB */
    val ram: Int,
    /** In GHz */
    val coreSpeed: Double,
    val timeStamp: Long
) {
    override fun toString(): String {
        return "Id: $uid; Operation System: $operationSystem; RAM: $ram; Core Speed: $coreSpeed; Timestamp: $timeStamp"
    }

}

fun sendCustomerInfoToServer(customer: CustomerInfo) {
    Server.send(customer.toString())
}

class Tourist(val name: String, val age: Int, val visitedCountries: Array<String>)

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val touristAdapter = moshi.adapter(Tourist::class.java)

val touristString = """
    {"name":"Jacky",
    "age":23, 
    "visitedCountry":["Israel","Argentina", "Malaysia"]}""".trimIndent()

val newTourist = touristAdapter.fromJson(touristString)

println(newTourist?.visitedCountries.joinToString())