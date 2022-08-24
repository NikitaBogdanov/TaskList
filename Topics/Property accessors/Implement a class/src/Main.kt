// write your class here
class LewisCarrollBook {
    var name: String = ""
        get() {
            println("The name of the book is $field")
            return field
        }
        set(value) {
            field = value
            println("Now, a book called $value")
        }
    val author: String = "Lewis Carroll"
        get() {
            println("The author of the book is $field")
            return field
        }
    var price: Int = 0
        get() = field
        set(value) {
            println("Putting a new price...")
            field = value
            println("The new price is $value")
        }
}
