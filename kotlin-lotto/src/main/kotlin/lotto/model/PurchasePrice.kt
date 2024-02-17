package lotto.model

@JvmInline
value class PurchasePrice(val price: Int) {
    init {
        require(price >= LOTTO_PRICE)
        require(price % LOTTO_PRICE == 0)
    }
}
