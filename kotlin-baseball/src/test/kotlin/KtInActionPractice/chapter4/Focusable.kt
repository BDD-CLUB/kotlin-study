package KtInActionPractice.chapter4

interface Focusable {

    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}