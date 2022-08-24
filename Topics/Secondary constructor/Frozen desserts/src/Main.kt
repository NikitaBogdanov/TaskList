// write the IceCreamOrder class here
class IceCreamOrder {
    var price: Int = 0

    constructor(popsicles: Int) {
        this.price = 7 * popsicles
    }
    constructor(scoops: Int, toppings: Int) {
        this.price = 5 * scoops + 2 * toppings
    }
}