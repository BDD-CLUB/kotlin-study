package lotto

class Lotto(val numbers: List<Number>) {
    init {
        require(numbers.size == 6)
    }
}


