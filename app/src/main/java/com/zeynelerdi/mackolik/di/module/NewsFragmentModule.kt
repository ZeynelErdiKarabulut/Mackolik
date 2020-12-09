package com.zeynelerdi.mackolik.ui.news

import com.zeynelerdi.mackolik.di.ViewModelKey
import dagger.Module

@Module
abstract class NewsFragmentModule {
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun provideMainViewModel(viewModel: NewsViewModel): NewsViewModel
}


