package com.jquiroga.imdumb.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jquiroga.imdumb.databinding.ItemMovieHorizontalBinding
import com.jquiroga.imdumb.features.home.model.MovieUiModel

class MovieHorizontalAdapter(
    private val onMovieClick: (MovieUiModel) -> Unit
) : ListAdapter<MovieUiModel, MovieHorizontalAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieHorizontalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieUiModel) {
            binding.textTitle.text = movie.title
            binding.textRating.text = movie.rating
            Glide.with(binding.imagePoster.context)
                .load(movie.posterUrl)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imagePoster)
            binding.root.setOnClickListener { onMovieClick(movie) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieUiModel>() {
            override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean =
                oldItem == newItem
        }
    }
}
