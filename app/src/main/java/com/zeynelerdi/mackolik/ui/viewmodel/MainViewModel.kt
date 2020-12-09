package com.zeynelerdi.mackolik.ui.main

import com.zeynelerdi.mackolik.data.manager.DataManagerImp
import com.zeynelerdi.mackolik.ui.common.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private var dataManagerImp: DataManagerImp) :
    BaseViewModel<MainNavigator>()
