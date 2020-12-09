package com.zeynelerdi.mackolik.di.module

import android.content.Context
import com.zeynelerdi.mackolik.app.App
import com.zeynelerdi.mackolik.data.manager.DataManagerImp
import com.zeynelerdi.mackolik.data.remote.service.ApiHelper
import com.zeynelerdi.mackolik.data.remote.service.ApiHelperImp
import com.zeynelerdi.mackolik.util.provider.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {

    @Provides
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    @Singleton
    fun providesRemoteService(context: Context): ApiHelper {
        return ApiHelper.createRetrofit(context)
    }


    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun providesFetchUseCase(context: Context): DataManagerImp {
        return DataManagerImp(
            provideCompositeDisposable(),
            ApiHelperImp(providesRemoteService(context))
        )
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()

}