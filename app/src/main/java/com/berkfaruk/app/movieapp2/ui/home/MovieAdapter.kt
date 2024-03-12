package com.berkfaruk.app.movieapp2.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkfaruk.app.movieapp2.MyDiffUtil
import com.berkfaruk.app.movieapp2.databinding.ItemMovieBinding
import com.berkfaruk.app.movieapp2.domain.model.SearchModel
import com.berkfaruk.app.movieapp2.utils.downloadFromUrl
import com.berkfaruk.app.movieapp2.utils.placeHolderProgressBar

class MovieAdapter(var movieList: List<SearchModel>?): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        movieList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movieList?.let {
            holder.binding.movieTitle.text = it[position].Title
            holder.binding.movieGenre.text = it[position].Type
            holder.binding.moviePoster.downloadFromUrl(it[position].Poster, placeHolderProgressBar(holder.itemView.context))
        }

        holder.itemView.setOnClickListener {view ->

            movieList?.let {
                val action = FragmentHomeDirections.actionFragmentHomeToDetailFragment()
                action.imdbId = it[position].imdbID
                Navigation.findNavController(view).navigate(action)

            }
        }

    }

    fun setData(newData: List<SearchModel>?) {

        movieList?.let { movieList2 ->
            newData?.let { newData2 ->
                val diffResult = DiffUtil.calculateDiff(MyDiffUtil(movieList2,newData2))
                movieList = newData
                diffResult.dispatchUpdatesTo(this@MovieAdapter)
            }
        }


    }
}