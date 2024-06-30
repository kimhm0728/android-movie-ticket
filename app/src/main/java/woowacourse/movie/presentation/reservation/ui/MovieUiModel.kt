package woowacourse.movie.presentation.reservation.ui

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningDate
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieUiModel(
    val id: Long,
    val title: String,
    @DrawableRes
    val posterImage: Int,
    val startScreeningDate: String,
    val endScreeningDate: String,
    private val screeningDate: ScreeningDate,
    val runningTime: Int,
    val description: String,
) {
    fun screeningDates(): List<String> {
        return screeningDate.screeningDates().map { it.format("yyyy-MM-dd") }
    }

    fun screeningTimes(position: Int): List<String> {
        return screeningDate.screeningTimes(position).map { it.format("HH:mm") }
    }

    companion object {
        fun from(movie: Movie): MovieUiModel {
            return movie.run {
                MovieUiModel(
                    id,
                    title,
                    posterImage,
                    screeningDate.startDate.format("yyyy.M.d"),
                    screeningDate.endDate.format("yyyy.M.d"),
                    screeningDate,
                    runningTime,
                    description,
                )
            }
        }

        private fun LocalDate.format(pattern: String) = format(DateTimeFormatter.ofPattern(pattern))

        private fun LocalTime.format(pattern: String) = format(DateTimeFormatter.ofPattern(pattern))
    }
}
