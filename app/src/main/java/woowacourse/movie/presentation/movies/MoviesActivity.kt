package woowacourse.movie.presentation.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presentation.movies.adapter.MoviesAdapter

class MoviesActivity : AppCompatActivity() {
    private val viewModel by viewModels<MoviesViewModel> { MoviesViewModelFactory(R.drawable.img_advertisement) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initializeView()
    }

    private fun initializeView() {
        val adapter = MoviesAdapter(onClickReservationButton = {
            TODO()
        })
        viewModel.movies.observe(this) {
            adapter.updateMovies(it)
        }
    }
}
