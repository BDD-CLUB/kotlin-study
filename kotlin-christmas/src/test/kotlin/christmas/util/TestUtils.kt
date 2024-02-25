package christmas.util

import org.assertj.core.api.AbstractStringAssert
import org.assertj.core.api.Assertions

fun String.assertContains(message: String): AbstractStringAssert<*> = Assertions.assertThat(this).contains(message)
