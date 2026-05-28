package com.jquiroga.imdumb.features.detail.view


import com.jquiroga.domain.usecase.GetMovieDetailUseCase
import com.jquiroga.imdumb.core.platform.BasePresenter
import com.jquiroga.imdumb.features.detail.mapper.MovieDetailUiMapper
import com.jquiroga.imdumb.features.detail.model.MovieDetailUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val movieDetailUiMapper: MovieDetailUiMapper
) : BasePresenter<DetailContract.View>(), DetailContract.Presenter {

    private var currentDetail: MovieDetailUiModel? = null

    override fun loadMovieDetail(movieId: Int) {
        view?.showLoading()
        getMovieDetailUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(movieDetailUiMapper::toDetailUiModel)
            .subscribe(
                { detail ->
                    currentDetail = detail
                    view?.hideLoading()
                    view?.showContent(detail)
                },
                { error ->
                    view?.hideLoading()
                    view?.showError(error.message ?: "Failed to load movie detail")
                }
            ).addTo(compositeDisposable)
    }

    override fun onRecommendClicked() {
        currentDetail?.let { view?.openRecommendBottomSheet(it) }
    }

    override fun onRetry(movieId: Int) {
        loadMovieDetail(movieId)
    }
}
