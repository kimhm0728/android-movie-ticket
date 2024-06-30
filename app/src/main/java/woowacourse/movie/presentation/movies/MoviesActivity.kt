package woowacourse.movie.presentation.movies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityMoviesBinding
import woowacourse.movie.presentation.movies.adapter.MoviesAdapter
import woowacourse.movie.presentation.reservation.ReservationActivity

class MoviesActivity : AppCompatActivity() {
    private val viewModel by viewModels<MoviesViewModel> { MoviesViewModelFactory(R.drawable.img_advertisement) }
    private val binding by lazy { ActivityMoviesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        initializeView()
    }

    private fun initializeView() {
        val adapter = MoviesAdapter(onClickReservationButton = {
            val intent = ReservationActivity.newIntent(this, it)
            startActivity(intent)
        })

        binding.movieList.adapter = adapter
        viewModel.movies.observe(this) {
            adapter.updateMovies(it)
        }
    }
}
