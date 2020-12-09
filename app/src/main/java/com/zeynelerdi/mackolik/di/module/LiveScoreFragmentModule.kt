package com.zeynelerdi.mackolik.ui.livescore

import com.zeynelerdi.mackolik.di.ViewModelKey
import dagger.Module

@Module
abstract class LiveScoreFragmentModule {
    @ViewModelKey(LiveScoreViewModel::class)
    internal abstract fun provideMainViewModel(viewModel: LiveScoreViewModel): LiveScoreViewModel
}


