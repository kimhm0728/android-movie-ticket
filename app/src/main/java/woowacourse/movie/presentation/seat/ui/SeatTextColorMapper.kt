package woowacourse.movie.presentation.seat.ui

import woowacourse.movie.R
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.SeatRating

fun Seat.getTextColor(): Int {
    return when (SeatRating.from(this)) {
        SeatRating.B -> R.color.purple
        SeatRating.S -> R.color.green
        SeatRating.A -> R.color.blue
    }
}
