package woowacourse.movie.presentation.movies.ui

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.Movie
import java.time.LocalDate

sealed interface MoviesUiModel {
    val moviesViewType: MoviesViewType

    class MovieUiModel(
        val id: Long,
        val title: String,
        @DrawableRes
        val posterImage: Int,
        val startScreeningDate: LocalDate,
        val endScreeningDate: LocalDate,
        val runningTime: Int,
        override val moviesViewType: MoviesViewType = MoviesViewType.MOVIE,
    ) : MoviesUiModel {
        companion object {
            fun from(movie:Movie): MoviesUiModel {
                return movie.run { MovieUiModel(id, title, posterImage, startScreeningDate, endScreeningDate, runningTime) }
            }
        }
    }

    class AdvertisementUiModel(
        @DrawableRes
        val advertisementImage: Int,
        override val moviesViewType: MoviesViewType = MoviesViewType.ADVERTISEMENT,
    ) : MoviesUiModel
}
