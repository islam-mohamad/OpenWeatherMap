package com.sal3awy.weather

import io.reactivex.Flowable
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

@Test
fun testt(){
    Flowable.interval(1, 100000, TimeUnit.MILLISECONDS)
        .buffer(5)
        .delay(3, TimeUnit.SECONDS)
        .doOnNext{
            print("size: ${it.size} $it")
        }.test()
}