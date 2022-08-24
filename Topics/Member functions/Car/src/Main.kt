class Car(val make: String, val year: Int) {

    var speed: Int = 0

    // write the methods here
    fun accelerate() {
        this.speed += 5
    }
    fun decelerate() {
        if (this.speed >= 5) this.speed -= 5 else this.speed = 0
    }

}