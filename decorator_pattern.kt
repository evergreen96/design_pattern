
/*
decorator pattern :
객체에 추가요소를 동적으로 추가할 때
subclass 보다 더 유연하게 동작

장점
유연성: 객체에 동적으로 책임을 추가할 수 있음
확장성: 기존 코드를 변경하지 않고도 새로운 기능 추가 가능
불필요한 클래스 수 감소: 모든 조합에 대한 클래스를 따로 생성할 필요 없음
단일 책임 원칙 (SRP) 준수: 클래스별로 하나의 책임만 관리

단점
데코레이터로 감싸지는 객체가 많아지면 객체의 복잡성 증가
데코레이터가 많아지면 디버깅이나 로직 추적이 어려워질 수 있음

데코레이터 패턴 사용 추천 상황 (언제 쓰면 좋을까?)
객체에 동적으로 여러 옵션이나 기능을 추가할 때
클래스의 서브클래스를 만드는 방식이 비효율적이거나, 너무 많은 서브클래스가 발생할 때
객체의 기능을 쉽게 추가하거나 제거하면서 변경 가능한 구조를 만들고 싶을 때
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