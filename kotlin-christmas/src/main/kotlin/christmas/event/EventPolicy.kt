package christmas.event

import christmas.menu.Menu
import christmas.menu.MenuType
import christmas.menu.MenuWithCount
import christmas.model.Price
import christmas.model.UserOrderInfo
import org.assertj.core.util.VisibleForTesting
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month

private const val MIN_EVENT_PRICE = 10_000

enum class EventPolicy {
    GiveawayEventPolicy {
        override fun isOwnSupported(userOrderInfo: UserOrderInfo) = userOrderInfo.totalPrice >= 120_000

        override fun getBenefit(userOrderInfo: UserOrderInfo): EventType =
            EventType.Giveaway(MenuWithCount(Menu.샴페인, 1), this.getPolicyName())

        override fun getPolicyName() = "증정 이벤트"
    },

    ChristmasDiscountPolicy {
        override fun isOwnSupported(userOrderInfo: UserOrderInfo) =
            userOrderInfo.estimatedVisitDate.isBefore(LocalDate.of(2023, 12, 26))

        override fun getBenefit(userOrderInfo: UserOrderInfo) =
            EventType.Discount(
                Price(1000 + 100 * (userOrderInfo.estimatedVisitDate.dayOfMonth - 1)),
                this.getPolicyName()
            )

        override fun getPolicyName() = "크리스마스 디데이 할인"
    },

    WeekdayDiscountPolicy {
        private val discountPricePerMenu = 2023
        private val availableDiscountDayOfWeek = listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY
        )

        override fun isOwnSupported(userOrderInfo: UserOrderInfo) =
            availableDiscountDayOfWeek.contains(userOrderInfo.estimatedVisitDate.dayOfWeek)

        override fun getBenefit(userOrderInfo: UserOrderInfo) =
            EventType.Discount(
                Price(userOrderInfo.getSumPriceOfMenu(MenuType.Dessert) * discountPricePerMenu),
                this.getPolicyName()
            )

        override fun getPolicyName() = "평일 할인"
    },

    WeekendDiscountPolicy {
        private val discountPricePerMenu = 2023
        private val availableDiscountDayOfWeek = listOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)

        override fun isOwnSupported(userOrderInfo: UserOrderInfo) =
            availableDiscountDayOfWeek.contains(userOrderInfo.estimatedVisitDate.dayOfWeek)

        override fun getBenefit(userOrderInfo: UserOrderInfo) =
            EventType.Discount(
                Price(userOrderInfo.getSumPriceOfMenu(MenuType.Main) * discountPricePerMenu),
                this.getPolicyName()
            )

        override fun getPolicyName() = "주말 할인"
    },

    SpecialDiscountPolicy {
        private val discountPrice = 1000
        private val availableDiscountDay = listOf(
            LocalDate.of(2023, 12, 3),
            LocalDate.of(2023, 12, 10),
            LocalDate.of(2023, 12, 17),
            LocalDate.of(2023, 12, 24),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 31),
        )

        override fun isOwnSupported(userOrderInfo: UserOrderInfo) =
            availableDiscountDay.contains(userOrderInfo.estimatedVisitDate)

        override fun getBenefit(userOrderInfo: UserOrderInfo) =
            EventType.Discount(Price(discountPrice), this.getPolicyName())

        override fun getPolicyName() = "특별 할인"
    },
    ;

    fun isSupported(userOrderInfo: UserOrderInfo) =
        userOrderInfo.totalPrice >= MIN_EVENT_PRICE &&
                isInEventDate(userOrderInfo) &&
                isOwnSupported(userOrderInfo)

    private fun isInEventDate(userOrderInfo: UserOrderInfo) =
        userOrderInfo.estimatedVisitDate.year == 2023 && userOrderInfo.estimatedVisitDate.month == Month.DECEMBER

    @VisibleForTesting
    internal abstract fun isOwnSupported(userOrderInfo: UserOrderInfo): Boolean

    abstract fun getBenefit(userOrderInfo: UserOrderInfo): EventType

    protected abstract fun getPolicyName(): String
}
