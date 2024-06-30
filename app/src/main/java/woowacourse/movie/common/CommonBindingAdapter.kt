package woowacourse.movie.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object CommonBindingAdapter {
    @BindingAdapter("imgRes")
    @JvmStatic
    fun ImageView.setImageResourceId(resId: Int) {
        setImageResource(resId)
    }
}
