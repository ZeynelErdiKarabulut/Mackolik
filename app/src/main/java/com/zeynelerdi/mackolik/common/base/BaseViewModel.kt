package com.zeynelerdi.mackolik.ui.common.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseViewModel<N : BaseNavigator> : ViewModel() {

    var mNavigator: WeakReference<N?>? = null

    var navigator: N?
        get() {
            return mNavigator?.get()
        }
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    @Inject
    lateinit var disposable: CompositeDisposable

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }

    fun checkResponse(
        message: String?,
        goBack: Boolean = false
    ) {
        message?.let {
            navigator?.showFail(message, goBack)
        }
    }

    val checkResponse: ( message: String?, goBack: Boolean) -> Unit =
        { message: String?, goBack ->
            checkResponse(message, goBack)
        }
}