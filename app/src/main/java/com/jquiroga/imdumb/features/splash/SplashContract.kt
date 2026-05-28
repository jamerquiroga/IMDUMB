package com.jquiroga.imdumb.features.splash

interface SplashContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun navigateToMain()
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadInitialConfig()
    }
}
