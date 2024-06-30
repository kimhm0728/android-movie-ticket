package woowacourse.movie.domain

interface MovieRepository {
    fun findAll(): List<Movie>

    fun find(id: Long): Movie?
}
