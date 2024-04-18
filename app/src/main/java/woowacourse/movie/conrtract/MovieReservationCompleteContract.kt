package woowacourse.movie.conrtract

import woowacourse.movie.model.MovieContent
import woowacourse.movie.model.Ticket
import woowacourse.movie.view.BaseView

interface MovieReservationCompleteContract {
    interface View : BaseView {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun setUpTicketUi(ticket: Ticket)
    }

    interface Presenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpTicket(reservationCount: Int)
    }
}