package woowacourse.movie.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import woowacourse.movie.databinding.ItemAdvertisementBinding
import woowacourse.movie.databinding.ItemMovieBinding
import woowacourse.movie.presentation.movies.ui.MoviesUiModel
import woowacourse.movie.presentation.movies.ui.MoviesViewType

class MoviesAdapter(
    private val onClickReservationButton: OnClickReservationButton,
) : Adapter<MoviesViewHolder>() {
    private var movies: List<MoviesUiModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(MoviesViewType.from(viewType)) {
            MoviesViewType.MOVIE -> {
                val binding = ItemMovieBinding.inflate(inflater, parent, false)
                MoviesViewHolder.MovieViewHolder(binding, onClickReservationButton)
            }
            MoviesViewType.ADVERTISEMENT -> {
                val binding = ItemAdvertisementBinding.inflate(inflater, parent, false)
                MoviesViewHolder.AdvertisementViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        when(holder) {
            is MoviesViewHolder.MovieViewHolder -> holder.bind(movies[position] as MoviesUiModel.MovieUiModel)
            is MoviesViewHolder.AdvertisementViewHolder -> holder.bind(movies[position] as MoviesUiModel.AdvertisementUiModel)
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun getItemViewType(position: Int): Int = movies[position].moviesViewType.viewType

    fun updateMovies(movies: List<MoviesUiModel>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}
