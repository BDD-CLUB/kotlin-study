package christmas.view

import christmas.util.ERROR_PREFIX

class OutputView {

    fun printReadDateErrorMessage() = println("$ERROR_PREFIX 유효하지 않은 날짜입니다. 다시 입력해 주세요.")

    fun printReadMenuErrorMessage() = println("$ERROR_PREFIX 유효하지 않은 주문입니다. 다시 입력해 주세요.")
}
