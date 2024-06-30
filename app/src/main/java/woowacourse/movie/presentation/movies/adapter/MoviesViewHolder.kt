package woowacourse.movie.presentation.movies.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.databinding.ItemAdvertisementBinding
import woowacourse.movie.databinding.ItemMovieBinding
import woowacourse.movie.presentation.movies.ui.MoviesUiModel

sealed class MoviesViewHolder(view: View) : ViewHolder(view) {
    class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val onClickReservationButton: OnClickReservationButton,
    ) : MoviesViewHolder(binding.root) {
        fun bind(movieUiModel: MoviesUiModel.MovieUiModel) {
            binding.movie = movieUiModel
            binding.reservationButton.setOnClickListener {
                onClickReservationButton(movieUiModel.id)
            }
        }
    }

    class AdvertisementViewHolder(private val binding: ItemAdvertisementBinding) :
        MoviesViewHolder(binding.root) {
        fun bind(advertisementUiModel: MoviesUiModel.AdvertisementUiModel) {
            binding.advertisement = advertisementUiModel
        }
    }
}

typealias OnClickReservationButton = (Long) -> Unit
