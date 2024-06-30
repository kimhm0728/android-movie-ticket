package woowacourse.movie.domain

class SeatTable(
    private val row: Int = ROW_SIZE,
    private val col: Int = COL_SIZE,
) {
    private val table: List<List<Seat>> = List(row) { List(col) { Seat(row, col) } }

    companion object {
        private const val ROW_SIZE = 5
        private const val COL_SIZE = 4
    }
}
