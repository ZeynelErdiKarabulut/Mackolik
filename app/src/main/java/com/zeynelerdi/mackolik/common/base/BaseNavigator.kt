package com.zeynelerdi.mackolik.ui.common.base

interface BaseNavigator {
    fun showLoading()
    fun hideLoading()
    fun showFail(message: String, goBack: Boolean = false)
}