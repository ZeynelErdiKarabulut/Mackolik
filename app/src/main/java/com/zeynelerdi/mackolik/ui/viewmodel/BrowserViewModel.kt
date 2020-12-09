package com.zeynelerdi.mackolik.ui.browser

import com.zeynelerdi.mackolik.data.manager.DataManagerImp
import com.zeynelerdi.mackolik.ui.common.base.BaseViewModel
import javax.inject.Inject

class BrowserViewModel @Inject constructor(private var dataManagerImp: DataManagerImp) :
    BaseViewModel<BrowserNavigator>()
