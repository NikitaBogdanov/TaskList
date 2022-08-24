import java.awt.Color

class Kitty {
    // write here
    var color: String = ""
    var age: Int = 0

    constructor(_color: String) {
        this.color = _color
    }

    constructor(_age: Int) {
        this.age = _age
    }

    constructor(_color: String, _age: Int) {
        this.color = _color
        this.age = _age
    }

    constructor(_age: Int, _color: String) {
        this.color = _color
        this.age = _age
    }
}