package woowacourse.movie.model.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.dto.Date
import woowacourse.movie.model.movieContent

class MovieContentsImplTest {
    @BeforeEach
    fun setUp() {
        MovieContentsImpl.deleteAll()
    }

    @Test
    fun `영화_정보를_저장한다`() {
        // given

        // when
        val id = MovieContentsImpl.save(movieContent)
        val actual = MovieContentsImpl.find(id)

        // then
        assertThat(actual.imageId).isEqualTo(0)
        assertThat(actual.title).isEqualTo("해리 포터와 마법사의 돌")
        assertThat(actual.screeningDate).isEqualTo(Date(2024, 3, 1))
        assertThat(actual.runningTime).isEqualTo(152)
        assertThat(actual.synopsis).isEqualTo("해리")
    }

    @Test
    fun `특정 id의 영화 정보를 가져온다`() {
        // given
        MovieContentsImpl.save(movieContent.copy(title = "1"))
        MovieContentsImpl.save(movieContent.copy(title = "2"))
        val id = MovieContentsImpl.save(movieContent.copy(title = "3"))

        // when
        val actual = MovieContentsImpl.find(id)

        // then
        assertThat(actual.title).isEqualTo("3")
    }

    @Test
    fun `유효하지 않은 id인 경우 빈 영화 정보를 가져온다`() {
        // given

        // when
        val actual = MovieContentsImpl.find(-1)

        // then
        assertThat(actual.imageId).isEqualTo(0)
        assertThat(actual.title).isEqualTo("오류가 발생했습니다.")
        assertThat(actual.screeningDate).isEqualTo(Date(0, 0, 0))
        assertThat(actual.runningTime).isEqualTo(0)
        assertThat(actual.synopsis).isEmpty()
    }

    @Test
    fun `모든 영화 정보를 가져온다`() {
        // given
        MovieContentsImpl.save(movieContent.copy(title = "1"))
        MovieContentsImpl.save(movieContent.copy(title = "2"))
        MovieContentsImpl.save(movieContent.copy(title = "3"))

        // when
        val actual = MovieContentsImpl.findAll()

        // then
        assertThat(actual.size).isEqualTo(3)
        assertThat(actual[0].title).isEqualTo("1")
        assertThat(actual[1].title).isEqualTo("2")
        assertThat(actual[2].title).isEqualTo("3")
    }

    @Test
    fun `모든 영화 정보를 삭제한다`() {
        // given
        MovieContentsImpl.save(movieContent.copy(title = "1")) // id : 0
        MovieContentsImpl.save(movieContent.copy(title = "2")) // id : 1
        MovieContentsImpl.save(movieContent.copy(title = "3")) // id : 2

        // when
        MovieContentsImpl.deleteAll()
        val actual = MovieContentsImpl.findAll()

        // then
        assertThat(actual).isEmpty()
    }
}
