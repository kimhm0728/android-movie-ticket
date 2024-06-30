package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import java.time.LocalDate

val dummyMovie =
    List(100) {
        Movie(
            id = it.toLong(),
            title = "해리 포터와 마법사의 돌 ${it + 1}",
            posterImage = R.drawable.img_movie_poster,
            startScreeningDate = LocalDate.of(2024, 3, 1),
            endScreeningDate = LocalDate.of(2024, 3, 20),
            runningTime = 152,
            description =
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, " +
                    "판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다. ",
        )
    }
