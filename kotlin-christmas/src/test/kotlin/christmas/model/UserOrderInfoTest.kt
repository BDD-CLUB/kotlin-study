package christmas.model

import christmas.menu.Menu
import christmas.menu.MenuType
import christmas.menu.MenuWithCount
import christmas.util.assertContains
import christmas.validation.ChristmasException
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class UserOrderInfoTest {
    @Nested
    inner class VisitDayTest {
        @Test
        fun `visitday는 1일 ~ 31일 안이어야 한다`() {
            assertThrows<ChristmasException> { UserOrderInfo.VisitDay(0) }
            assertDoesNotThrow { UserOrderInfo.VisitDay(1) }
            assertDoesNotThrow { UserOrderInfo.VisitDay(31) }
            assertThrows<ChristmasException> { UserOrderInfo.VisitDay(32) }
        }
    }

    @Nested
    inner class UserOrderMenuTest {
        @Test
        fun `userOrderMenu 개수는 총 20개를 넘으면 안된다`() {
            assertDoesNotThrow {
                UserOrderInfo.UserOrderMenu(listOf(generateWithoutDrink(1)))
            }
            assertDoesNotThrow {
                UserOrderInfo.UserOrderMenu(listOf(generateWithoutDrink(20))) // 20개
            }
            assertDoesNotThrow {
                UserOrderInfo.UserOrderMenu((1..19).map { generateWithoutDrink(1) }) // 19개
            }
            assertDoesNotThrow {
                UserOrderInfo.UserOrderMenu((1..10).map { generateWithoutDrink(2) }) // 20개
            }
            assertDoesNotThrow {
                UserOrderInfo.UserOrderMenu((1..4).map { generateWithoutDrink(5) }) // 20개
            }

            assertThrows<ChristmasException>("totalMenuCount는 20개를 넘을 수 없습니다.") {
                UserOrderInfo.UserOrderMenu((1..21).map { generateWithoutDrink(1) }) // 21개
            }
            assertThrows<ChristmasException>("totalMenuCount는 20개를 넘을 수 없습니다.") {
                UserOrderInfo.UserOrderMenu((1..11).map { generateWithoutDrink(2) }) // 22개
            }
            assertThrows<ChristmasException>("totalMenuCount는 20개를 넘을 수 없습니다.") {
                UserOrderInfo.UserOrderMenu((1..8).map { generateWithoutDrink(3) }) // 24개
            }
            assertThrows<ChristmasException>("totalMenuCount는 20개를 넘을 수 없습니다.") {
                UserOrderInfo.UserOrderMenu(listOf(generateWithoutDrink(21))) // 21개
            }
        }

        @Test
        fun `음료만 주문하면 안된다`() {
            assertDoesNotThrow {
                UserOrderInfo.UserOrderMenu(listOf(generateWithoutDrink()))
            }
            assertDoesNotThrow {
                UserOrderInfo.UserOrderMenu(listOf(generateWithoutDrink(), generateDrink()))
            }

            for (i in 1..19) {
                assertThrows<ChristmasException> {
                    UserOrderInfo.UserOrderMenu((1..i).map { generateDrink() })
                }.message.assertContains("음료만 주문할 수 없습니다.")
            }
        }

        /**
         * 이것도 모킹하면 더 좋지만 체력 이슈로 패스
         */
        private fun generateWithoutDrink(count: Int = 1) =
            MenuWithCount(Menu.entries.filter { it.menuType != MenuType.Drink }.random(), count)

        private fun generateDrink() = MenuWithCount(Menu.entries.filter { it.menuType == MenuType.Drink }.random(), 1)

    }
}
