package com.jquiroga.imdumb.core.platform

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V> {

    protected var view: V? = null
        private set

    protected val compositeDisposable = CompositeDisposable()

    open fun attach(view: V) {
        this.view = view
    }

    open fun detach() {
        view = null
        compositeDisposable.clear()
    }
}
