package step2.global

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

fun createUniqueRandomNumbers(count: Int): String =
        generateSequence { Randoms.pickNumberInRange(1, 9) }
                .distinct()
                .take(count)
                .joinToString("")

fun getThreeUniqueNumbers(): String = Console.readLine() ?: throw IllegalArgumentException()
