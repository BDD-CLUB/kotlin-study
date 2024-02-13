package KtInActionPractice

import org.junit.jupiter.api.Test


class KtInActionPart3Test {

    @Test
    fun testJoinToString() {
        val list = listOf(1, 2, 3)
        println(joinToString(list, "; ", "(", ")"))
    }

    @Test
    fun testJoinToString2() {
        val collection = listOf(1, 2, 3)
        println(joinToString(collection, separator = " ", prefix = " ", postfix = "."))
    }

    @Test
    fun utilPropertyTest() {
        println(UNIT_LINE_SEPARATOR)
    }

    @Test
    fun extensionFun1() {
        println("Kotlin".lastChar())
    }

    @Test
    fun extensionFun2() {
        val list = listOf(1, 2, 3)
        println(list.joinToString2(" "))
    }

    @Test
    fun extensionFun3() {
        val list = listOf("one", "two", "three", "eight")
        println(list.joinToString3(" "))
    }

    @Test
    fun spreadTest() {
        val list = arrayOf("String", "Java", "Kotlin")
        spread(list)
    }

    private fun spread(args: Array<String>) {
        println(listOf("args: ", *args))
    }

    @Test
    fun mapToTest() {
        val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
        println(map)
    }

    @Test
    fun splitTest() {
        println("12.345-6.A".split(".", "-"))
    }

    @Test
    fun parseTest() {
        val path = "/Users/yole/kotlin-book/chapter.adoc"
        parsePath(path)
    }

    private fun parsePath(path: String) {
        val directory = path.substringBeforeLast("/")
        val fullName = path.substringAfterLast("/")
        val fileName = fullName.substringBefore(".")
        val extension = path.substringAfterLast(".")
        println("Dir: $directory, name: $fileName, ext: $extension")
    }

    @Test
    fun StringTest() {
        println("""
            |   //
            |  //
            | /\
        """.trimIndent())
    }

    class User(
            val id: Int,
            val name: String,
            val address: String
    )

    private fun saveUser(user: User) {
        fun validate(user: User,
                     value: String,
                     fieldName: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Can't save User ${user.id}: empty $fieldName")
            }
        }

        validate(user, user.name, "Name")
        validate(user, user.address, "Address")
    }

    /*
    * Issue Entity 클래스에 몰빵 vs 디렉토리 나눠서 확장 함수로 관리? -> (p.138 list 3.14) 자바는 어쩔 수 없이 클래스 안에 몰빵해야 하는데 코틀린은 확장 함수 때문에 분리해서 관리할 수 있음.
    * Issue Builder vs 생성자 -> 커맨드 + P + p.108
    * */

    private fun saveUser2(user: User) {
        fun validate(value: String, fieldName: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Can't save User ${user.id}: empty $fieldName")
            }
        }

        validate(user.name, "Name")
        validate(user.address, "Address")
    }

}
