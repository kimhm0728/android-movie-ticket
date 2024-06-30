package woowacourse.movie.presentation.seat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import woowacourse.movie.domain.MovieRepository
import java.lang.IllegalArgumentException

class SeatSelectionViewModelFactory(
    private val movieRepository: MovieRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        if (modelClass.isAssignableFrom(SeatSelectionViewModel::class.java)) {
            return SeatSelectionViewModel(movieRepository) as T
        }
        throw IllegalArgumentException()
    }
}
