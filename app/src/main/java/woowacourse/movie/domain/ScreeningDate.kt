package woowacourse.movie.domain

import java.time.LocalDate

class ScreeningDate(
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    fun screeningDateRange(): List<LocalDate> {
        return generateSequence(startDate) { date ->
            if (date < endDate) date.plusDays(DAY_INTERVAL) else null
        }.toList()
    }

    companion object {
        private const val DAY_INTERVAL = 1L
    }
}
