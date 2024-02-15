package KtInActionPractice.chapter2

import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.StringReader
import java.util.TreeMap

class KtInActionPart2Test {

    @Test
    fun printHelloWorld() {
        println("Hello, World")
    }

    @Test
    fun maxTest() {
        println(max(3, 2))
        println(max(2,4))
    }

    /*
    * 식이 본문인 함수의 반환 타입만 생략이 가능하다.
    * */
    private fun max(a: Int, b: Int): Int = if (a > b) a else b

    @Test
    fun variable() {
        val languages = arrayListOf("Java")
        languages.add("Kotlin")
        println(languages)
    }

    @Test
    fun stringTemplate() {
        val name = "Kotlin"
        println("Hello, $name!")
        println("Hello, ${name}님 반가워용")
//        println("Hello, $name님 반가워용")
    }

    @Test
    fun classTest() {
        val person = Person("ggang9", false)
        println(person.name)
        println(person.isMarried)
        println(person)
    }

    class Person (
            val name: String,
            val isMarried: Boolean
    )

    @Test
    fun enumTest() {
        println(Color2.BLUE.rgb())
    }

    enum class Color {
        RED, ORANGE, YELLOW
    }

    enum class Color2(
            val r: Int, val g: Int, val b: Int
    ) {
        RED(255, 0, 0),
        ORANGE(0, 255, 0),
        BLUE(0, 0, 255)
        ;

        fun rgb() = (r * 256 + g) % 256 + b
    }

    @Test
    fun enumTest2() {
        println(getMnemonic(Color2.BLUE))
    }

    private fun getMnemonic(color: Color2) =
        when (color) {
            Color2.RED -> "Richard"
            Color2.BLUE -> "Battle"
            Color2.ORANGE -> "Of"
        }

    @Test
    fun testForLoop() {
        for (i in 1..100) {
            println(fizzBuzz(i))
        }
    }

    private fun fizzBuzz(i: Int) = when {
        i % 15 == 0 -> "FizzBuzz"
        i % 3 == 0 -> "Fizz"
        i % 5 == 0 -> "Buzz"
        else -> "${i}"
    }

    @Test
    fun testForLoop2() {
        for (i in 100 downTo 1 step 2) {
            println(fizzBuzz(i))
        }
    }

    @Test
    fun testMapIter() {
        val binaryReps = TreeMap<Char, String>()
        for (c in 'A'..'F') {
            val binary = Integer.toBinaryString(c.code)
            binaryReps[c] = binary
        }

        for ((letter, binary) in binaryReps) {
            println("$letter = $binary")
        }
    }

    @Test
    fun testWhenIn() {
        println(recognize('B'))
    }

    private fun recognize(c: Char) = when (c) {
        in '0'..'9' -> "It's a digit!"
        in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
        else -> "I don't know"
    }

    @Test
    fun readNumber() {
        println(readNumber(BufferedReader(StringReader("not a number"))))
    }

    private fun readNumber(reader: BufferedReader) {
        val number = try {
            Integer.parseInt(reader.readLine())
        } catch (e: NumberFormatException) {
            null
        }
    }


}