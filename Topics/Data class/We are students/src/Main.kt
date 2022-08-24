// write your data class here
data class Student(val name: String, val money: Int) {
    override fun equals(other: Any?): Boolean {
        if (other is Student) {
            return name == other.name && other.money < 1500
        }
        return false
    }
}