package christmas.model

enum class GiftMenu (
    val menu: Menu,
    val thresholdPrice: Int,
    val quantity: Int
){
    GIFT_CHAMPAGNE(Menu.CHAMPAGNE, 120_000, 1),
    ;

    companion object {
        fun getEligibleGift(totalPrice: Int): GiftMenu? {
            return GiftMenu.entries
                .filter { it.thresholdPrice < totalPrice }
                .maxByOrNull { it.thresholdPrice }
        }
    }
}
