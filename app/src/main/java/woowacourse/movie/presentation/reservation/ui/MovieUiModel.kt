package woowacourse.movie.presentation.reservation.ui

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieUiModel(
    val id: Long,
    val title: String,
    @DrawableRes
    val posterImage: Int,
    val startScreeningDate: String,
    val endScreeningDate: String,
    val screeningDateRange: List<String>,
    val runningTime: Int,
    val description: String,
) {
    companion object {
        fun from(movie: Movie): MovieUiModel {
            return movie.run {
                MovieUiModel(
                    id,
                    title,
                    posterImage,
                    screeningDate.startDate.format("yyyy.M.d"),
                    screeningDate.endDate.format("yyyy.M.d"),
                    screeningDate.screeningDateRange().map { it.format("yyyy-MM-dd") },
                    runningTime,
                    description,
                )
            }
        }

        private fun LocalDate.format(pattern: String) = format(DateTimeFormatter.ofPattern(pattern))
    }
}
