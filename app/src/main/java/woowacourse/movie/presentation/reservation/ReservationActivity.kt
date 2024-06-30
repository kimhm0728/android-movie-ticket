package woowacourse.movie.presentation.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.LocalMovieRepository
import woowacourse.movie.databinding.ActivityReservationBinding

class ReservationActivity : AppCompatActivity() {
    private val viewModel by viewModels<ReservationViewModel> {
        ReservationViewModelFactory(
            intent.getLongExtra(MOVIE_ID_KEY, MOVIE_ID_DEFAULT_VALUE),
            LocalMovieRepository()
        )
    }
    private val binding by lazy { ActivityReservationBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        viewModel.loadMovie()
        initializeView()
    }

    private fun initializeView() {
        viewModel.movieError.observe(this) {
            Toast.makeText(this, R.string.invalid_movie, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val MOVIE_ID_KEY = "movieId"
        private const val MOVIE_ID_DEFAULT_VALUE = -1L

        fun newIntent(context: Context, movieId: Long): Intent {
            return Intent(context, ReservationActivity::class.java)
                .putExtra(MOVIE_ID_KEY, movieId)
        }
    }
}
