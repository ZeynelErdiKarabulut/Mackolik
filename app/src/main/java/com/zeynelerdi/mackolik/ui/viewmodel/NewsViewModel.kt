package com.zeynelerdi.mackolik.ui.news

import android.os.Handler
import androidx.databinding.ObservableField
import com.zeynelerdi.mackolik.data.manager.DataManagerImp
import com.zeynelerdi.mackolik.data.remote.service.plusAssign
import com.zeynelerdi.mackolik.ui.common.base.BaseViewModel
import com.zeynelerdi.mackolik.ui.news.model.response.News
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class NewsViewModel @Inject constructor(private var dataManagerImp: DataManagerImp) :
    BaseViewModel<NewsNavigator>() {

    var newsList = ObservableField<List<News?>?>()

    fun getNews() {
        newsList.set(null)
        navigator?.showLoading()
        dataManagerImp.apiHelperImp.getNews()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                newsList.set(it?.data?.news)
                navigator?.hideLoading()
            }.also {
                disposable += it
            }
    }

    var setNewsClickListener: (news: News) -> Unit = { i: News ->
        navigator?.onNewsClick(i)
    }

}
