package woowacourse.movie.domain

import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val posterImage: Int,
    val startScreeningDate: LocalDate,
    val endScreeningDate: LocalDate,
    val runningTime: Int,
    val description: String,
)
