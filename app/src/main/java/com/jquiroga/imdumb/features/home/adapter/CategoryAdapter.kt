package com.jquiroga.imdumb.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jquiroga.imdumb.R
import com.jquiroga.imdumb.core.common.HorizontalSpacingDecoration
import com.jquiroga.imdumb.databinding.ItemCategorySectionBinding
import com.jquiroga.imdumb.features.home.model.MovieCategoryUiModel
import com.jquiroga.imdumb.features.home.model.MovieUiModel

class CategoryAdapter(
    private val sharedPool: RecyclerView.RecycledViewPool,
    private val onMovieClick: (MovieUiModel) -> Unit
) : ListAdapter<MovieCategoryUiModel, CategoryAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategorySectionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryViewHolder(
        private val binding: ItemCategorySectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var movieAdapter: MovieHorizontalAdapter? = null

        fun bind(category: MovieCategoryUiModel) {
            binding.textCategoryTitle.text = category.categoryName
            val adapter = movieAdapter ?: MovieHorizontalAdapter(onMovieClick).also {
                movieAdapter = it
                binding.recyclerMovies.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    setRecycledViewPool(sharedPool)
                    setHasFixedSize(true)
                    isNestedScrollingEnabled = false
                    val spacing = resources.getDimensionPixelSize(R.dimen.spacing_small)
                    if (itemDecorationCount == 0) {
                        addItemDecoration(HorizontalSpacingDecoration(spacing))
                    }
                    this@apply.adapter = it
                }
            }
            adapter.submitList(category.movies)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieCategoryUiModel>() {
            override fun areItemsTheSame(
                oldItem: MovieCategoryUiModel,
                newItem: MovieCategoryUiModel
            ): Boolean = oldItem.categoryName == newItem.categoryName

            override fun areContentsTheSame(
                oldItem: MovieCategoryUiModel,
                newItem: MovieCategoryUiModel
            ): Boolean = oldItem == newItem
        }
    }
}
