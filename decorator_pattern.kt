
/*
decorator pattern :
객체에 추가요소를 동적으로 추가할 때
subclass 보다 더 유연하게 동작
 */

//abstract로 할 필요는 없다.

abstract class Beverage{
    abstract val description: String

    abstract fun cost(): Double
}

abstract class CondimentDecorator: Beverage() {
    abstract var beverage: Beverage
}

class Espresso: Beverage() {

    override val description: String = "espresso"

    override fun cost(): Double {
        return 2.1
    }
}

class Americano: Beverage() {
    override val description: String = "americano"

    override fun cost(): Double {
        return 1.5
    }
}

class Moca(beverage: Beverage): CondimentDecorator() {
    override var beverage: Beverage = beverage
    override val description: String = beverage.description + " with moca"

    override fun cost(): Double {
        return beverage.cost() + 0.5
    }

}

fun main() {
    val beverage = Espresso()
    val moca = Moca(beverage)

    println(moca.description)
    println(moca.cost())

    val order2 = Americano()
    val moca2 = Moca(order2)

    println(moca2.description)
    println(moca2.cost())
}