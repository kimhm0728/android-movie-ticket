package woowacourse.movie.presentation.reservation.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.ReservationCount

@Parcelize
class ReservationBundle(
    val movieId: Long,
    val reservationCount: ReservationCount,
) : Parcelable
