package woowacourse.movie.conrtract

import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.presenter.BasePresenter
import woowacourse.movie.view.BaseView

interface MovieReservationCompleteContract {
    interface View : BaseView {
        fun setUpMovieContentUi(movieContent: MovieContent)

        fun setUpTicketUi(ticket: Ticket)
    }

    interface Presenter : BasePresenter {
        fun setUpMovieContent(movieContentId: Long)

        fun setUpTicket(reservationCount: Int)
    }
}
