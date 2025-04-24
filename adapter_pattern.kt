
/*
adapter pattern : 특정 클래스 인터페이스를 클라이언트에서 요구하는 다른 인터페이스로 바꾸는 것
인터페이스가 호환되지 않을 때 사용


객체 어댑터, 클래스 어댑터 존재
-> 클래스 어댑터는 다중 상속이 필요하다고 한다.
 */

interface DuckInAdapter {
    fun fly()
    fun quack()
}

class Mallarduck : DuckInAdapter {
    override fun fly() {
        TODO("Not yet implemented")
    }

    override fun quack() {
        TODO("Not yet implemented")
    }

}

interface Turkey {
    fun gobble()
    fun fly()
}

class WildDuck: Turkey {
    override fun gobble() {
        TODO("Not yet implemented")
    }

    override fun fly() {
        TODO("Not yet implemented")
    }

}


class TurkeyAdapter(private val turkey: Turkey): DuckInAdapter {

    override fun fly() {
        turkey.fly()
    }

    override fun quack() {
        turkey.gobble()
    }

}