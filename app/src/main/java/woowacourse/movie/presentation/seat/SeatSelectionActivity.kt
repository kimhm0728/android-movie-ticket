package woowacourse.movie.presentation.seat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.data.LocalMovieRepository
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.domain.Seat
import woowacourse.movie.presentation.reservation.ui.ReservationBundle
import woowacourse.movie.presentation.seat.ui.SeatUiModel
import woowacourse.movie.presentation.seat.ui.getTextColor

class SeatSelectionActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySeatSelectionBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<SeatSelectionViewModel> {
        SeatSelectionViewModelFactory(
            LocalMovieRepository(),
        )
    }

    private val seatViews: List<List<TextView>> by lazy {
        binding.seatTable
            .children
            .filterIsInstance<TableRow>()
            .map { it.children.filterIsInstance<TextView>().toList() }
            .toList()
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

    private fun initializeView() {
        setSeatRatingTextColor()
        setSeatClickListener()
        viewModel.movieError.observe(this) {
            Toast.makeText(this, R.string.invalid_movie, Toast.LENGTH_SHORT).show()
        }
        viewModel.selectedSeats.observe(this) {
            setSelectedSeatColor(it.seats)
        }
    }

    private fun setSeatRatingTextColor() {
        seatViews.forEachIndexed { row, rows ->
            rows.forEachIndexed { col, view ->
                val textColor = Seat(row, col).getTextColor()
                view.setTextColor(ContextCompat.getColor(this, textColor))
            }
        }
    }

    private fun setSeatClickListener() {
        seatViews.forEachIndexed { row, rows ->
            rows.forEachIndexed { col, view ->
                view.setOnClickListener { viewModel.selectSeat(row, col) }
            }
        }
    }

    private fun setSelectedSeatColor(seats: List<SeatUiModel>) {
        seatViews.flatten().forEach { it.setBackgroundColor(ContextCompat.getColor(this, UNSELECT_SEAT_COLOR)) }
        seats.forEach {
            seatViews[it.row][it.col].setBackgroundColor(ContextCompat.getColor(this, SELECT_SEAT_COLOR))
        }
    }

    companion object {
        private val UNSELECT_SEAT_COLOR = R.color.white
        private val SELECT_SEAT_COLOR = R.color.yellow
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
