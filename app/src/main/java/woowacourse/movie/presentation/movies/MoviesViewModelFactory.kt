package woowacourse.movie.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MoviesViewModelFactory(
    private val advertisementImage: Int,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(advertisementImage) as T
        }
        throw IllegalArgumentException()
    }
}
