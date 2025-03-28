/*
singleton_pattern
: 클래스를 인스턴스를 하나만 만들고 전역 접근을 제공

java - enum, kotlin-object를 이용하면 singleton이 된다.
 */

class Singleton {
    companion object {
        private var uniqueSingleton: Singleton? = null

        @Volatile
        private var volatileSingleton: Singleton? = null


        // 이 방식은 동기화 하는데 속도가 느려진다.
        // - uniqueSingleton을 미리 생성하는 것도 하나의 방법
        @Synchronized
        fun getInstance(): Singleton {
            if (uniqueSingleton == null) {
                uniqueSingleton = Singleton()
            }
            return uniqueSingleton!!
        }
        // 이렇게 double checking 하는 방식으로 하면 된다.
        fun getVolatileInstance(): Singleton {
            if (volatileSingleton == null) {
                synchronized(Singleton::class.java) {
                    if (volatileSingleton == null) {
                        volatileSingleton = Singleton()
                    }
                }
            }
            return volatileSingleton!!
        }
    }

    private constructor()
}




fun main() {

}