package com.jquiroga.imdumb.features.detail.view

import com.jquiroga.imdumb.features.detail.model.MovieDetailUiModel

interface DetailContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showContent(detail: MovieDetailUiModel)
        fun showError(message: String)
        fun openRecommendBottomSheet(detail: MovieDetailUiModel)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadMovieDetail(movieId: Int)
        fun onRecommendClicked()
        fun onRetry(movieId: Int)
    }
}
