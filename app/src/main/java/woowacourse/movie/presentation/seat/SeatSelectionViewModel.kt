package woowacourse.movie.presentation.seat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import woowacourse.movie.common.MutableSingleLiveData
import woowacourse.movie.common.SingleLiveData
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.domain.ReservationCount
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.SelectedSeats
import woowacourse.movie.presentation.reservation.ui.ReservationBundle
import woowacourse.movie.presentation.seat.ui.MovieUiModel
import woowacourse.movie.presentation.seat.ui.SelectedSeatsUiModel

class SeatSelectionViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _movie = MutableLiveData<MovieUiModel>()
    val movie: LiveData<MovieUiModel> get() = _movie

    private val _movieError = MutableSingleLiveData<Unit>()
    val movieError: SingleLiveData<Unit> get() = _movieError

    private var reservationCount = ReservationCount()

    private val _selectedSeats = MutableLiveData<SelectedSeats>(SelectedSeats())
    val selectedSeats = _selectedSeats.map { SelectedSeatsUiModel.from(it) }
    val totalPrice: LiveData<Int> = _selectedSeats.map { it.totalPrice() }

    fun loadMovie(reservationBundle: ReservationBundle) {
        val movie = movieRepository.find(reservationBundle.movieId)
        if (movie == null) {
            _movieError.setValue(Unit)
            return
        }
        _movie.value = MovieUiModel.of(movie)
        reservationCount = reservationBundle.reservationCount
    }

    fun selectSeat(
        row: Int,
        col: Int,
    ) {
        val selectedSeats = _selectedSeats.value ?: return
        _selectedSeats.value = selectedSeats.selectSeat(Seat(row, col))
    }
}
