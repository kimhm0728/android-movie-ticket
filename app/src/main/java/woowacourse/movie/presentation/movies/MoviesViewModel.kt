package woowacourse.movie.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.presentation.movies.ui.MoviesUiModel

class MoviesViewModel(
    private val advertisementImage: Int,
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val _movies = MutableLiveData<List<MoviesUiModel>>(emptyList())
    val movies: LiveData<List<MoviesUiModel>> get() = _movies

    fun loadMovies() {
        _movies.value = movieRepository.findAll().addAdvertisement()
    }

    private fun List<Movie>.addAdvertisement(): List<MoviesUiModel> {
        val movieUiModels = map { MoviesUiModel.MovieUiModel.from(it) }
        val advertisementUiModel = MoviesUiModel.AdvertisementUiModel(advertisementImage)
        return movieUiModels
            .windowed(ADVERTISEMENT_INTERVAL, step = ADVERTISEMENT_INTERVAL, partialWindows = true)
            .flatMap {
                if (it.size == ADVERTISEMENT_INTERVAL) it + advertisementUiModel else it
            }
    }

    companion object {
        private const val ADVERTISEMENT_INTERVAL = 3
    }
}
