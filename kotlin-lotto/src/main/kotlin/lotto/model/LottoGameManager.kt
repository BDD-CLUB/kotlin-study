package lotto.model

import camp.nextstep.edu.missionutils.Randoms
import lotto.controller.LottoController
import lotto.global.Component

@Component
class LottoGameManager(
        private val lottoController: LottoController
) {

    fun run() {
        // STEP1 > 구입금액 입력 받기
        val purchaseAmount = lottoController.purchaseLotto()

        // STEP2 > 구매 내역 출력
        val randomLottos = lottoController.announceLottoNumbers(purchaseAmount)

        // STEP3 > 당첨 번호 입력 받기
        val winningNumbers = lottoController.enterWinningNumbers()
        
        // STEP4 > 보너스 번호 입력 받기
        val bonusNumber = lottoController.enterBonusNumber()

        // STEP5 > 로또 당첨 내역 출력
        val lottoBuyer = LottoBuyer(
                randomLottos,
                winningNumbers,
                bonusNumber
        )

        val lottoResultList = lottoBuyer.compareLottoNumbers()
        lottoController.announceResult(lottoResultList, purchaseAmount)
    }


}