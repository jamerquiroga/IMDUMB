package com.jquiroga.imdumb.features.detail.view


import com.jquiroga.imdumb.core.platform.BasePresenter
import com.jquiroga.imdumb.features.detail.model.MovieDetailUiModel
import javax.inject.Inject

class DetailPresenter @Inject constructor() : BasePresenter<DetailContract.View>(), DetailContract.Presenter {

    private var currentDetail: MovieDetailUiModel? = null

    override fun loadMovieDetail(movieId: Int) {
        view?.showLoading()
    }

    override fun onRecommendClicked() {
        currentDetail?.let { view?.openRecommendBottomSheet(it) }
    }

    override fun onRetry(movieId: Int) {
        loadMovieDetail(movieId)
    }
}
