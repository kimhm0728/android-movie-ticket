package woowacourse.movie.data

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieRepository

class LocalMovieRepository(private val movies: List<Movie> = dummyMovie) : MovieRepository {
    override fun findAll(): List<Movie> {
        return movies
    }

    override fun find(id: Long): Movie? {
        return movies.find { it.id == id }
    }
}
