package com.berkfaruk.app.movieapp2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berkfaruk.app.movieapp2.databinding.ItemMovieBinding
import com.berkfaruk.app.movieapp2.domain.model.SearchModel
import com.berkfaruk.app.movieapp2.utils.downloadFromUrl
import com.berkfaruk.app.movieapp2.utils.placeHolderProgressBar

class MovieAdapter(val movieList: List<SearchModel>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.binding.movieTitle.text = movieList[position].Title
        holder.binding.movieGenre.text = movieList[position].Type
        holder.binding.moviePoster.downloadFromUrl(movieList[position].Poster, placeHolderProgressBar(holder.itemView.context))

    }
}