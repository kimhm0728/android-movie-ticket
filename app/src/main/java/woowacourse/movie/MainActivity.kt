package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.dao.MovieContentsImpl
import woowacourse.movie.model.Date
import woowacourse.movie.model.MovieContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MovieContentsImpl.save(
            MovieContent(
                R.drawable.movie_poster,
                "해리 포터와 마법사의 돌",
                Date(2024, 3, 1),
                152,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, " +
                    "판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다. ",
            ),
        )

        val movieContentList = findViewById<ListView>(R.id.movie_content_list)
        movieContentList.adapter = MovieContentListAdapter(this)
    }
}
