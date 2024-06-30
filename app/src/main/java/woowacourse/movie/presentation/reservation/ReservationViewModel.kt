package woowacourse.movie.presentation.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.movie.common.MutableSingleLiveData
import woowacourse.movie.common.SingleLiveData
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ReservationCount
import woowacourse.movie.presentation.reservation.ui.ReservationListener

class ReservationViewModel(
    private val movieId: Long,
    private val movieRepository: MovieRepository
) : ViewModel(), ReservationListener {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val _movieError = MutableSingleLiveData<Unit>()
    val movieError: SingleLiveData<Unit> get() = _movieError

    private val _reservationCount = MutableLiveData<ReservationCount>()
    val reservationCount: LiveData<ReservationCount> get() = _reservationCount

    private val _navigateEvent = MutableSingleLiveData<Unit>()
    val navigateEvent: SingleLiveData<Unit> get() = _navigateEvent

    fun loadMovie() {
        val movie = movieRepository.find(movieId)
        movie ?: run {
            _movieError.setValue(Unit)
            return
        }
        _movie.value = movie
    }

    override fun onIncreaseReservationCount() {
        var reservationCount = _reservationCount.value ?: return
        _reservationCount.value = ++reservationCount
    }

    override fun onDecreaseReservationCount() {
        var reservationCount = _reservationCount.value ?: return
        _reservationCount.value = --reservationCount
    }

    override fun navigateSeatSelection() {
        _navigateEvent.setValue(Unit)
    }
}
