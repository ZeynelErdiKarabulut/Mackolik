package com.zeynelerdi.mackolik.ui.news

import com.zeynelerdi.mackolik.ui.common.base.BaseNavigator
import com.zeynelerdi.mackolik.ui.news.model.response.News

interface NewsNavigator : BaseNavigator{
    fun onNewsClick(news: News)
}