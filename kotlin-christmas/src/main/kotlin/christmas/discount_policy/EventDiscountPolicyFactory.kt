package christmas.discount_policy

fun createEventDiscountPolicy(): List<EventDiscountPolicy> =
        listOf(ChristmasDdayDiscountPolicy(), SpecialDiscountPolicy(), WeekdayDiscountPolicy(), WeekendDiscountPolicy())


