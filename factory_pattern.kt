/*
simple factory : 패턴은 아니지만 관용구처럼 쓰인다.
 */

abstract class Pizza


class CheesePizza: Pizza() {}
class ClamPizza: Pizza() {}
class VeggiePizza: Pizza() {}


class NYCheesePizza: Pizza() {}
class NYClamPizza: Pizza() {}
class NYVeggiePizza: Pizza() {}

class LACheesePizza: Pizza() {}
class LAClamPizza: Pizza() {}
class LAVeggiePizza: Pizza() {}

class SimplePizzaFactory {

    fun createPizza(type: String): Pizza? {
       return  when(type) {
            "cheese" -> CheesePizza()
           "Clam" -> ClamPizza()
           "veggie" -> VeggiePizza()
           else -> null
        }
    }
}

class SimplePizzaStore {
    val simplePizzaFactory = SimplePizzaFactory()

    fun orderPizza(type: String): Pizza? {
        val pizza = simplePizzaFactory.createPizza(type)

        /*
        prepare, bake, cut, box
         */
        return pizza
    }
}

/*
factory method pattern
: 객체 생성을 캡슐화, 서브 클래스에서 어떤 클래스를 만들지 결정함으로써 객체 생성을 캡슐화

장점
Factory Method 패턴의 가장 큰 장점은 지금까지 본 것처럼 수정에 닫혀있고 확장에는 열려있는 OCP 원칙을 지킬 수 있다.
다른 객체 없이 상속을 통해서 구현하므로 비교적 메모리를 아낄 수 있다.
단점
간단한 기능을 사용할 때보다 많은 클래스를 정의해야 하기 때문에 코드량이 증가한다.
 */


/*
디자인 원칙: Dependency Inversion Principle (DIP)
추상화된 것에 의존하게 만들고, 구상 클래스에 의존하지 않게 만든다.

DIP를 지키는 방법
변수에 구상 클래스의 레퍼런스를 저장하지 않는다.
구상 클래스에서 유도된 클래스를 만들지 않는다.
베이스 클래스에 이미 구현되어 있는 메소드를 오버라이드 하지 않는다.
 */


abstract class PizzaStore {

    // 변경이 일어나는 pizza를 만드는 행위는 factory method에서 처리
    // subClass가 결정하도록 하기
    protected abstract fun createPizza(type: String): Pizza?

    fun orderPizza(type: String): Pizza? {
        val pizza = createPizza(type)
        /*
        prepare, bake, cut, box
       */
        return pizza
    }
}

class NYPizzaStore: PizzaStore() {
    override fun createPizza(type: String): Pizza? {
        return  when(type) {
            "cheese" -> NYCheesePizza()
            "Clam" -> NYClamPizza()
            "veggie" -> NYVeggiePizza()
            else -> null
        }
    }
}

class LAPizzaStore: PizzaStore() {
    override fun createPizza(type: String): Pizza? {
        return  when(type) {
            "cheese" -> LACheesePizza()
            "Clam" -> LAClamPizza()
            "veggie" -> LAVeggiePizza()
            else -> null
        }
    }
}


/*
추상 팩토리 패턴
: 구상 클래스에서 의존하지 않고도 서로 연관되거나
  의존적인 객체로 이루어진 제품군을 생산하는 인터페이스.
 */

interface Dough {
    override fun toString(): String
}

class ThinCrustDough : Dough {
    override fun toString(): String {
        return "Thin Crust Dough"
    }
}

interface Cheese {
    override fun toString(): String
}

class BlueCheese : Cheese {
    override fun toString(): String {
        return "Blue Cheese"
    }
}

interface PizzaIngredientFactory {
    fun createDough(): Dough?
    fun createCheese(): Cheese?
}

class NYPizzaIngredientFactory : PizzaIngredientFactory {
    override fun createDough(): Dough {
        return ThinCrustDough()
    }


    override fun createCheese(): Cheese {
        return BlueCheese()
    }

}


class UpgradedNYPizzaStore : PizzaStore() {
    override fun createPizza(type: String): Pizza? {
        var pizza: Pizza? = null
        val ingredientFactory: PizzaIngredientFactory = NYPizzaIngredientFactory()

        return  when(type) {
            "cheese" -> CheesePizza(/*ingredientFactory*/)
            "Clam" -> ClamPizza(/*ingredientFactory*/)
            "veggie" -> VeggiePizza(/*ingredientFactory*/)
            else -> null
        }
    }
}

fun main() {
    val simplePizzaStore = SimplePizzaStore()

    val nyPizzaStore = NYPizzaStore()
    println(nyPizzaStore.orderPizza("cheese"))

    val laPizzaStore = LAPizzaStore()
    println(laPizzaStore.orderPizza("veggie"))
}