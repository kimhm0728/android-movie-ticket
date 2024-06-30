package woowacourse.movie.domain

data class Seat(val row: Int, val col: Int) {
    val amount: Int get() = SeatRating.from(this).amount
}
