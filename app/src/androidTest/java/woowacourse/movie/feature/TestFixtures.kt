package woowacourse.movie.feature

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.anything
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.dto.MovieContent

const val firstMovieContentId = 0L
val firstMovieContent = MovieContentsImpl.findAll().first()
val firstMovieContentItem: DataInteraction = onData(anything()).inAdapterView(withId(R.id.movie_content_list)).atPosition(0)

fun MovieContent.screeningDateMessage(): String {
    return screeningDate.run { "상영일: %d.%d.%d".format(year, month, day) }
}

fun MovieContent.runningTimeMessage(): String {
    return "러닝타임: %d분".format(runningTime)
}

fun MovieContent.dateMessage(): String {
    return screeningDate.run { "%d.%d.%d".format(year, month, day) }
}

fun Int.reservationCountMessage(): String {
    return "일반 %d명".format(this)
}

fun Int.reservationAmountMessage(): String {
    return "%,d원 (현장 결제)".format(this * 13000)
}

fun view(id: Int): ViewInteraction {
    return onView(withId(id))
}

fun DataInteraction.child(id: Int): DataInteraction {
    return onChildView(withId(id))
}

fun ViewInteraction.equalText(text: String) {
    check(matches(withText(text)))
}

fun DataInteraction.equalText(text: String) {
    check(matches(withText(text)))
}

fun ViewInteraction.scroll(): ViewInteraction {
    return perform(scrollTo())
}

fun ViewInteraction.click(): ViewInteraction {
    return perform(ViewActions.click())
}
