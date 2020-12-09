package com.zeynelerdi.mackolik.ui.browser


import com.zeynelerdi.mackolik.di.ViewModelKey
import dagger.Module

@Module
abstract class BrowserActivityModule {
    @ViewModelKey(BrowserViewModel::class)
    internal abstract fun provideMainViewModel(viewModel: BrowserViewModel): BrowserViewModel
}


