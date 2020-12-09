package com.zeynelerdi.mackolik.ui.main


import com.zeynelerdi.mackolik.di.ViewModelKey
import dagger.Module

@Module
abstract class MainActivityModule {
    @ViewModelKey(MainViewModel::class)
    internal abstract fun provideMainViewModel(viewModel: MainViewModel): MainViewModel
}


