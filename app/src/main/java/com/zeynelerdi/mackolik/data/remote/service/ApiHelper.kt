package com.zeynelerdi.mackolik.data.remote.service

import android.content.Context
import com.zeynelerdi.mackolik.BuildConfig
import com.zeynelerdi.mackolik.ui.livescore.model.response.LiveScoreResponse
import com.zeynelerdi.mackolik.ui.news.model.response.NewsResponse
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiHelper {

    companion object {

        fun createRetrofit(context: Context): ApiHelper {

            val builder = OkHttpClient.Builder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.addInterceptor(LoggingInterceptor())
            builder.addInterceptor(ChuckInterceptor(context))
            val client = builder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiHelper::class.java)

        }
    }

    @GET("/news")
    fun getNews(): Observable<NewsResponse>

    @GET("/matches")
    fun getLiveScores(): Observable<LiveScoreResponse>

}