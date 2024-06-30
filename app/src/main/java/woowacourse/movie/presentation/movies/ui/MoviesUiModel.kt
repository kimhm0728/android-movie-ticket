package woowacourse.movie.presentation.movies.ui

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.Movie
import woowacourse.movie.presentation.movies.ui.MoviesUiModel.MovieUiModel.Companion.toString
import java.time.LocalDate
import java.time.format.DateTimeFormatter

sealed interface MoviesUiModel {
    val moviesViewType: MoviesViewType

    class MovieUiModel(
        val id: Long,
        val title: String,
        @DrawableRes
        val posterImage: Int,
        val startScreeningDate: String,
        val endScreeningDate: String,
        val runningTime: Int,
        override val moviesViewType: MoviesViewType = MoviesViewType.MOVIE,
    ) : MoviesUiModel {
        companion object {
            fun from(movie: Movie): MoviesUiModel {
                return movie.run {
                    MovieUiModel(
                        id,
                        title,
                        posterImage,
                        screeningDate.startDate.format(),
                        screeningDate.endDate.format(),
                        runningTime,
                    )
                }
            }

            private fun LocalDate.format() = format(DateTimeFormatter.ofPattern("yyyy.M.d"));
        }
    }

    class AdvertisementUiModel(
        @DrawableRes
        val advertisementImage: Int,
        override val moviesViewType: MoviesViewType = MoviesViewType.ADVERTISEMENT,
    ) : MoviesUiModel
}
