/*
observer pattern  :
한 객체의 상태가 변하면 그 객체에 의존하는 다른 객체에게 연락이 가고
내용이 갱신되는 상태
 */


interface Subject {
    fun registerObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}

interface Observer {
    fun update(temperature: Float, humility: Float, pressure: Float)
}

interface DisplayElement {
    fun display()
}

class WeatherData: Subject {
    private val observers = mutableListOf<Observer>()

    private var temperature: Float = -999f
    private var humility: Float = -999f
    private var pressure: Float = -999f

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    // 만약 동일한 클래스가 여러 개 들어오고 하나만 빼야 한다면?
    // 방법은 여러가지 이다. [index를 이용한 방법], [key를 이용한 방법]
    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        for (observer in observers) {
            observer.update(temperature = temperature, humility = humility, pressure = pressure)
        }
    }

    fun setMeasurements(temperature: Float, humility: Float, pressure: Float) {
        this.temperature = temperature
        this.humility = humility
        this.pressure = pressure

        notifyObservers()
    }

}


class CurrentConditionsDisplay: Observer, DisplayElement {
    private var temperature: Float = -999f
    private var humility: Float = -999f
    private var pressure: Float = -999f

    override fun update(temperature: Float, humility: Float, pressure: Float) {
        this.temperature = temperature
        this.humility = humility
        this.pressure = pressure

        display()

    }

    override fun display() {
       println("display $temperature $humility $pressure")
    }
}

// weatherData를 가지고 있고 정보가 왔을 때 pulldisplay가 필요한 것만 업데이트 가능하게도 가능

class PullDisplay: Observer, DisplayElement {
    private var temperature: Float = -999f
    private var humility: Float = -999f
    private var pressure: Float = -999f

    override fun update(temperature: Float, humility: Float, pressure: Float) {
        this.temperature = temperature
        this.humility = humility
        this.pressure = pressure

        display()

    }

    override fun display() {
        println("display $temperature $humility $pressure")
    }
}

fun main() {
    val weatherData = WeatherData()

    val conditionsDisplay = CurrentConditionsDisplay()

    weatherData.registerObserver(conditionsDisplay)
    weatherData.setMeasurements(10f, 10f, 10f)
}