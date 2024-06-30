package woowacourse.movie.presentation.reservation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import woowacourse.movie.data.MovieRepository
import java.lang.IllegalArgumentException

class ReservationViewModelFactory(
    private val movieRepository: MovieRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReservationViewModel::class.java)) {
            return ReservationViewModel(movieRepository) as T
        }
        throw IllegalArgumentException()
    }
}
