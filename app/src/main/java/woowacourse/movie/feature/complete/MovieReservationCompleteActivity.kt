package woowacourse.movie.feature.complete

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.base.BaseActivity
import woowacourse.movie.constants.MovieContentKey
import woowacourse.movie.constants.MovieReservationKey
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.ui.DateUi

class MovieReservationCompleteActivity :
    BaseActivity<MovieReservationCompleteContract.Presenter>(),
    MovieReservationCompleteContract.View {
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val screeningDateText by lazy { findViewById<TextView>(R.id.screening_date_text) }
    private val reservationCountText by lazy { findViewById<TextView>(R.id.reservation_count_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)

        val movieContentId = movieContentId()
        val reservationCount = reservationCount()
        if (movieContentId == MOVIE_CONTENT_ID_DEFAULT_VALUE || reservationCount == RESERVATION_COUNT_DEFAULT_VALUE) {
            handleError()
            return
        }

        setUpUi(movieContentId, reservationCount)
    }

    override fun initializePresenter() = MovieReservationCompletePresenter(this, MovieContentsImpl)

    private fun movieContentId() = intent.getLongExtra(MovieContentKey.ID, MOVIE_CONTENT_ID_DEFAULT_VALUE)

    private fun reservationCount() = intent.getIntExtra(MovieReservationKey.COUNT, RESERVATION_COUNT_DEFAULT_VALUE)

    override fun handleError() {
        Log.e(TAG, "Invalid Key")
        Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
        finish()
    }

    private fun setUpUi(
        movieContentId: Long,
        reservationCount: Int,
    ) {
        presenter.setUpMovieContent(movieContentId)
        presenter.setUpTicket(reservationCount)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setUpMovieContentUi(movieContent: MovieContent) {
        movieContent.run {
            titleText.text = title
            screeningDateText.text =
                DateUi.dateMessage(screeningDate, this@MovieReservationCompleteActivity)
        }
    }

    override fun setUpTicketUi(ticket: Ticket) {
        ticket.run {
            reservationCountText.text =
                resources.getString(R.string.reservation_count).format(reservationCount.count)
            reservationAmountText.text =
                resources.getString(R.string.reservation_amount).format(amount())
        }
    }

    companion object {
        private val TAG = MovieReservationCompleteActivity::class.simpleName
        private const val MOVIE_CONTENT_ID_DEFAULT_VALUE = -1L
        private const val RESERVATION_COUNT_DEFAULT_VALUE = -1
    }
}
