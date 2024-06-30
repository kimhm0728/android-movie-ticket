package woowacourse.movie.domain

class ReservationCount(val count: Int) {
    operator fun inc(): ReservationCount {
        return ReservationCount((count + 1).coerceAtMost(MAX_COUNT))
    }

    operator fun dec(): ReservationCount {
        return ReservationCount((count - 1).coerceAtLeast(MIN_COUNT))
    }

    companion object {
        private const val MAX_COUNT = 30
        private const val MIN_COUNT = 1
    }
}
