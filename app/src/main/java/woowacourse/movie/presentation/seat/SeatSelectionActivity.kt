package woowacourse.movie.presentation.seat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.data.LocalMovieRepository
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.presentation.reservation.ui.ReservationBundle

class SeatSelectionActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySeatSelectionBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SeatSelectionViewModel> {
        SeatSelectionViewModelFactory(
            LocalMovieRepository(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.vm = viewModel
        val reservationBundle = reservationBundle()
        if (reservationBundle == null) {
            showErrorSnackBar()
        } else {
            viewModel.loadMovie(reservationBundle)
        }
        initializeView()
    }

    private fun reservationBundle(): ReservationBundle? {
        val reservationBundle =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(RESERVATION_BUNDLE_KEY, ReservationBundle::class.java)
            } else {
                intent.getParcelableExtra(RESERVATION_BUNDLE_KEY) as? ReservationBundle
            }
        return reservationBundle
    }

    private fun initializeView() {
        viewModel.movieError.observe(this) {
            Toast.makeText(this, R.string.invalid_movie, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showErrorSnackBar() {
        Snackbar
            .make(
                binding.root,
                getString(R.string.invalid_reservation_bundle),
                Snackbar.LENGTH_INDEFINITE,
            )
            .setAction(getString(R.string.confirm)) { finish() }
            .show()
    }

    companion object {
        private const val RESERVATION_BUNDLE_KEY = "reservationBundle"

        fun newIntent(
            context: Context,
            reservationBundle: ReservationBundle,
        ): Intent {
            return Intent(context, SeatSelectionActivity::class.java)
                .putExtra(RESERVATION_BUNDLE_KEY, reservationBundle)
        }
    }
}
