package christmas.service

import christmas.event.EventPolicy
import christmas.event.EventType
import christmas.model.UserBenefitInfo
import christmas.model.UserOrderInfo

object ChristmasService {

    private val eventPolicyList = EventPolicy.entries

    fun getBenefitInfo(userOrderInfo: UserOrderInfo) = UserBenefitInfo(getBenefitList(userOrderInfo))

    private fun getBenefitList(userOrderInfo: UserOrderInfo) =
        mutableListOf<EventType>().apply {
            for (eventPolicy in eventPolicyList) {
                if (eventPolicy.isSupported(userOrderInfo)) {
                    this.add(eventPolicy.getBenefit(userOrderInfo))
                }
            }
        }.toList()
}
