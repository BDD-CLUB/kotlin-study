package christmas.badge

import christmas.model.Price
import christmas.validation.ChristmasException

enum class Badge(val minBenefitAmount: UInt) {
    별(5_000u),
    트리(10_000u),
    산타(20_000u),
    ;

    companion object {
        fun of(benefitAmount: Price) =
            when (benefitAmount.value.toUInt()) {
                in 0u until 별.minBenefitAmount -> null
                in 별.minBenefitAmount until 트리.minBenefitAmount -> 별
                in 트리.minBenefitAmount until 산타.minBenefitAmount -> 트리
                in 산타.minBenefitAmount until UInt.MAX_VALUE -> 산타
                else -> throw ChristmasException("말도 안되는 혜택 금액입니다. 혜택 금액: $benefitAmount")
            }
    }
}
