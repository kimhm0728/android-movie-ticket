package woowacourse.movie.presentation.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.movie.common.MutableSingleLiveData
import woowacourse.movie.common.SingleLiveData
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.domain.Movie

class ReservationViewModel(
    private val movieId: Long,
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val _movieError = MutableSingleLiveData<Unit>()
    val movieError: SingleLiveData<Unit> get() = _movieError

    fun loadMovie() {
        val movie = movieRepository.find(movieId)
        movie ?: run {
            _movieError.setValue(Unit)
            return
        }
        _movie.value = movie
    }
}
