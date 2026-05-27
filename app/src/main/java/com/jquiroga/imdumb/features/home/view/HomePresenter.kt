package com.jquiroga.imdumb.features.home.view

import com.jquiroga.imdumb.core.platform.BasePresenter
import com.jquiroga.imdumb.features.home.model.MovieUiModel
import javax.inject.Inject

class HomePresenter @Inject constructor(): BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun loadCategories() {
        view?.showLoading()
    }

    override fun onMovieClicked(movie: MovieUiModel) {
        view?.navigateToDetail(movie)
    }

    override fun onRetry() {
        loadCategories()
    }
}
