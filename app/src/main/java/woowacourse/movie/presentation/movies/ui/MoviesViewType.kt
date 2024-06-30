package woowacourse.movie.presentation.movies.ui

enum class MoviesViewType(val viewType: Int) {
    MOVIE(0),
    ADVERTISEMENT(1),
    ;

    companion object {
        fun from(viewType: Int): MoviesViewType {
            return entries.find { it.viewType == viewType }
                ?: throw IllegalArgumentException("올바르지 않은 뷰 타입입니다.")
        }
    }
}
