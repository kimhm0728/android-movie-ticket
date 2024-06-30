package woowacourse.movie.presentation.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.LocalMovieRepository
import woowacourse.movie.databinding.ActivityReservationBinding

class ReservationActivity : AppCompatActivity() {
    private val binding by lazy { ActivityReservationBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ReservationViewModel> {
        ReservationViewModelFactory(
            intent.getLongExtra(MOVIE_ID_KEY, MOVIE_ID_DEFAULT_VALUE),
            LocalMovieRepository(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        viewModel.loadMovie()
        initializeView()
    }

    private fun initializeView() {
        viewModel.movieError.observe(this) {
            Toast.makeText(this, R.string.invalid_movie, Toast.LENGTH_SHORT).show()
        }

        viewModel.navigateEvent.observe(this) {
            TODO()
        }
    }

    companion object {
        private const val MOVIE_ID_KEY = "movieId"
        private const val MOVIE_ID_DEFAULT_VALUE = -1L

        fun newIntent(
            context: Context,
            movieId: Long,
        ): Intent {
            return Intent(context, ReservationActivity::class.java)
                .putExtra(MOVIE_ID_KEY, movieId)
        }
    }
}
