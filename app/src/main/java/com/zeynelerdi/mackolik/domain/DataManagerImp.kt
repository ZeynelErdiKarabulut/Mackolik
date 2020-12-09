package com.zeynelerdi.mackolik.data.manager

import com.zeynelerdi.mackolik.data.remote.service.ApiHelperImp
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DataManagerImp @Inject internal constructor(
    val compositeDisposable: CompositeDisposable,
    val apiHelperImp: ApiHelperImp
) : DataManager