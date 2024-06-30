package woowacourse.movie.domain

data class Movie(
    val id: Long,
    val title: String,
    val posterImage: Int,
    val screeningDate: ScreeningDate,
    val runningTime: Int,
    val description: String,
)
