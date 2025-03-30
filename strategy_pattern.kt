/*
전략 패턴 : 알고리즘군을 정의하고 캡슐화하여 각각의 알고리즘군을 수정해서 쓸 수 있게 하는 것

각 기능을 interface로 분리해서 처리하지 않는 이유
- interface를 구현하는 클래스가 많아질 때 전부 interface에 영향을 받는다.
- 구현하는 클래스에서 중복 코드가 발생한다. 그래서 재사용성이 떨어진다. 
*/


interface FlyBehavior {
    fun fly()
}

interface QuackBehavior {
    fun quack()
}


class FlyWithWings(): FlyBehavior {
    override fun fly() {
       println("Fly with wings")
    }
}

class FlyWithEngine: FlyBehavior {
    override fun fly() {
        println("fly with engine")
    }
}

class QuackWithSpeaker(): QuackBehavior {
    override fun quack() {
        println("quack with speacker")
    }
}

abstract class Duck {
    lateinit var flyBehavior: FlyBehavior
    lateinit var quackBehavior: QuackBehavior

    fun performFly()  {
        flyBehavior.fly()
    }

    fun performQuack() {
        quackBehavior.quack()
    }
}

class MallardDuck(): Duck() {

    constructor(flyBehavior: FlyBehavior, quackBehavior: QuackBehavior) : this() {
        this.flyBehavior = flyBehavior
        this.quackBehavior = quackBehavior
    }
}

fun main() {
    val mallardDuck = MallardDuck(flyBehavior = FlyWithWings(), quackBehavior = QuackWithSpeaker())
    mallardDuck.performFly()
    mallardDuck.performQuack()

    mallardDuck.flyBehavior = FlyWithEngine()
    mallardDuck.performFly()
}

