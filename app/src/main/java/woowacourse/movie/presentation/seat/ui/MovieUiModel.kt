package woowacourse.movie.presentation.seat.ui

import woowacourse.movie.domain.Movie

class MovieUiModel(val title: String) {
    companion object {
        fun of(movie: Movie): MovieUiModel {
            return MovieUiModel(movie.title)
        }
    }
}
