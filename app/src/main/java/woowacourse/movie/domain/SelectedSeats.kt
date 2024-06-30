package woowacourse.movie.domain

class SelectedSeats(val seats: List<Seat> = emptyList()) {
    fun selectSeat(seat: Seat): SelectedSeats {
        val seats = if (seats.contains(seat)) seats.minus(seat) else seats.plus(seat)
        return SelectedSeats(seats)
    }

    fun totalPrice(): Int = seats.sumOf { it.amount }
}
