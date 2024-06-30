package woowacourse.movie.data

import woowacourse.movie.domain.Movie

interface MovieRepository {
    fun findAll(): List<Movie>

    fun find(id: Long): Movie?
}
