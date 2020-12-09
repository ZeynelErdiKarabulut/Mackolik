package com.zeynelerdi.mackolik.data.remote.service

import com.zeynelerdi.mackolik.ui.livescore.model.response.LiveScoreResponse
import com.zeynelerdi.mackolik.ui.news.model.response.NewsResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiHelperImp @Inject constructor(
    private val apiHelper: ApiHelper
) {
    fun getNews(): Observable<Resource<NewsResponse>> {
        return apiHelper.getNews().map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())

    }

    fun getLiveScores(): Observable<Resource<LiveScoreResponse>> {
        return apiHelper.getLiveScores().map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())

    }
}