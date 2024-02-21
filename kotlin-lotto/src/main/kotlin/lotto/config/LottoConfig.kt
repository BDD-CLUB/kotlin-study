package lotto.config

import lotto.generator.NumberGenerator
import lotto.generator.RandomNumberGenerator
import lotto.view.ConsoleLottoView
import lotto.view.LottoView

fun view(): LottoView {
    return ConsoleLottoView()
}

fun numberGenerator(): NumberGenerator {
    return RandomNumberGenerator()
}
