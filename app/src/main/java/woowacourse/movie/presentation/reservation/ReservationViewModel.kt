package woowacourse.movie.presentation.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.movie.common.MutableSingleLiveData
import woowacourse.movie.common.SingleLiveData
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.domain.ReservationCount
import woowacourse.movie.presentation.reservation.ui.MovieUiModel
import woowacourse.movie.presentation.reservation.ui.ReservationListener

class ReservationViewModel(
    private val movieId: Long,
    private val movieRepository: MovieRepository,
) : ViewModel(), ReservationListener {
    private val _movie = MutableLiveData<MovieUiModel>()
    val movie: LiveData<MovieUiModel> = _movie

    private val _movieError = MutableSingleLiveData<Unit>()
    val movieError: SingleLiveData<Unit> get() = _movieError

    private val _reservationCount = MutableLiveData<ReservationCount>(ReservationCount())
    val reservationCount: LiveData<ReservationCount> get() = _reservationCount

    private val _navigateEvent = MutableSingleLiveData<Unit>()
    val navigateEvent: SingleLiveData<Unit> get() = _navigateEvent

    private val _screeningTimes = MutableLiveData<List<String>>()
    val screeningTimes: LiveData<List<String>> get() = _screeningTimes

    fun loadMovie() {
        val movie =
            movieRepository.find(movieId) ?: run {
                _movieError.setValue(Unit)
                return
            }
        _movie.value = MovieUiModel.from(movie)
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

    fun selectScreeningDate(position: Int) {
        val movie = _movie.value ?: return
        _screeningTimes.value = movie.screeningTimes(position)
    }
}
