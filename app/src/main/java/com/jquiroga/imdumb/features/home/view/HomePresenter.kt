package com.jquiroga.imdumb.features.home.view

import com.jquiroga.domain.usecase.GetCategoriesUseCase
import com.jquiroga.imdumb.core.platform.BasePresenter
import com.jquiroga.imdumb.features.home.mapper.MovieUiMapper
import com.jquiroga.imdumb.features.home.model.MovieUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val movieUiMapper: MovieUiMapper
): BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun loadCategories() {
        view?.showLoading()
        getCategoriesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { categories -> categories.map(movieUiMapper::toCategoryUiModel) }
            .subscribe(
                { categories ->
                    view?.hideLoading()
                    if (categories.isEmpty()) {
                        view?.showEmpty()
                    } else {
                        view?.showCategories(categories)
                    }
                },
                { error ->
                    view?.hideLoading()
                    view?.showError(error.message ?: "Unknown error")
                }
            ).addTo(compositeDisposable)
    }

    override fun onMovieClicked(movie: MovieUiModel) {
        view?.navigateToDetail(movie)
    }

    override fun onRetry() {
        loadCategories()
    }
}
