package woowacourse.movie.presentation.seat.ui

import woowacourse.movie.domain.Seat

class SeatUiModel(val row: Int, val col: Int) {
    companion object {
        fun from(seat: Seat): SeatUiModel {
            return SeatUiModel(seat.row, seat.col)
        }
    }
}
