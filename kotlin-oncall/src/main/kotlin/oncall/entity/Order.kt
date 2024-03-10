package oncall.entity

import oncall.exception.requireOncall

const val MAX_NICKNAME_SIZE = 5
const val MIN_ORDER_MEMBER = 5
const val MAX_ORDER_MEMBER = 35

class Order(private val order: List<String>) {
    init {
        requireOncall(order.all { it.length <= MAX_NICKNAME_SIZE }) { "닉네임은 최대 ${MAX_NICKNAME_SIZE}자 여야 합니다." }
        requireOncall(order.size == order.distinct().size) { "중복된 이름이 있습니다." }
        requireOncall(order.size in MIN_ORDER_MEMBER..MAX_ORDER_MEMBER) { "비상 근무자는 ${MIN_ORDER_MEMBER}명 이상 ${MAX_ORDER_MEMBER}명 이하여야 합니다. 비상 근무자 수 : ${order.size}" }
    }

    private val size = order.size
    private var index = 0
    private var queueMember: String? = null

    fun getNextTurnMember(previousMember: String?) =
        if (isQueueMemberTurn(previousMember)) {
            val nextTurnMember = queueMember
            queueMember = null
            nextTurnMember
        } else {
            getNextTurnMember(previousMember, this.getNextMember())
        }

    private fun getNextTurnMember(previousMember: String?, nextMember: String) =
        if (previousMember == nextMember) {
            // 만약 다음 멤버와 바로 전 멤버가 같다면 큐에 넣고 그 다음 멤버를 순서로 올린다.
            queueMember = nextMember
            this.getNextMember()
        } else {
            nextMember
        }

    private fun isQueueMemberTurn(previousMember: String?) = queueMember != null && queueMember != previousMember

    private fun getNextMember() = this.order[(index++) % this.size]
}
