package com.jquiroga.imdumb.features.splash

import android.util.Log
import com.jquiroga.domain.usecase.FetchRemoteConfigUseCase
import com.jquiroga.imdumb.core.platform.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val fetchRemoteConfigUseCase: FetchRemoteConfigUseCase
) : BasePresenter<SplashContract.View>(), SplashContract.Presenter {

    override fun loadInitialConfig() {
        view?.showLoading()
        fetchRemoteConfigUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view?.hideLoading()
                    view?.navigateToMain()
                },
                { error ->
                    Log.e(TAG, "Error initializing remote config", error)
                    view?.hideLoading()
                    view?.navigateToMain()
                }
            )
            .addTo(compositeDisposable)
    }

    companion object {
        private const val TAG = "SplashPresenter"
    }
}
