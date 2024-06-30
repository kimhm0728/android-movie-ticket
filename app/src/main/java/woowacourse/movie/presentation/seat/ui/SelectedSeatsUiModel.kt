package woowacourse.movie.presentation.seat.ui

import woowacourse.movie.domain.SelectedSeats

class SelectedSeatsUiModel(val seats: List<SeatUiModel>) {
    companion object {
        fun from(selectedSeats: SelectedSeats): SelectedSeatsUiModel {
            return SelectedSeatsUiModel(selectedSeats.seats.map { SeatUiModel.from(it) })
        }
    }
}
