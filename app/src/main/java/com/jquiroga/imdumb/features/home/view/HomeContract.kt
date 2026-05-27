package com.jquiroga.imdumb.features.home.view

import com.jquiroga.imdumb.features.home.model.MovieCategoryUiModel
import com.jquiroga.imdumb.features.home.model.MovieUiModel

interface HomeContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showCategories(categories: List<MovieCategoryUiModel>)
        fun showError(message: String)
        fun showEmpty()
        fun navigateToDetail(movie: MovieUiModel)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadCategories()
        fun onMovieClicked(movie: MovieUiModel)
        fun onRetry()
    }
}
