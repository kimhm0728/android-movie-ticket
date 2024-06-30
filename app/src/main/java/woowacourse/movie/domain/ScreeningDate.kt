package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class ScreeningDate(
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    fun screeningDates(): List<LocalDate> {
        return generateSequence(startDate) { date ->
            if (date < endDate) date.plusDays(DAY_INTERVAL) else null
        }.toList()
    }

    fun screeningTimes(position: Int): List<LocalTime> {
        val startHour =
            if (screeningDates()[position].isWeekend()) WEEKEND_START_HOUR else WEEKDAY_START_HOUR
        return (startHour..END_HOUR step HOUR_INTERVAL).map {
            if (it == END_HOUR) LocalTime.of(0, 0) else LocalTime.of(it, 0)
        }
    }

    private fun LocalDate.isWeekend(): Boolean {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
    }

    companion object {
        private const val DAY_INTERVAL = 1L
        private const val HOUR_INTERVAL = 2
        private const val WEEKEND_START_HOUR = 9
        private const val WEEKDAY_START_HOUR = 10
        private const val END_HOUR = 24
    }
}
