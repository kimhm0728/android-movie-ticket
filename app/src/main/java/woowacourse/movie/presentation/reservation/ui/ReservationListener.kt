package woowacourse.movie.presentation.reservation.ui

interface ReservationListener {
    fun onIncreaseReservationCount()

    fun onDecreaseReservationCount()

    fun navigateSeatSelection()
}
