package woowacourse.movie.domain

enum class SeatRating(private val rows: List<Int>, val amount: Int) {
    B(listOf(0, 1), 10_000),
    S(listOf(2, 3), 15_000),
    A(listOf(4), 12_000),
    ;

    companion object {
        private const val INVALID_SEAT_MESSAGE = "좌석에 해당하는 등급이 존재하지 않습니다."

        fun from(seat: Seat): SeatRating {
            return entries.find { it.rows.contains(seat.row) } ?: throw IllegalArgumentException(INVALID_SEAT_MESSAGE)
        }
    }
}
