package woowacourse.movie.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import woowacourse.movie.domain.MovieRepository
import java.lang.IllegalArgumentException

class MoviesViewModelFactory(
    private val advertisementImage: Int,
    private val movieRepository: MovieRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(advertisementImage, movieRepository) as T
        }
        throw IllegalArgumentException()
    }
}
