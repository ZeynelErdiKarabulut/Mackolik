package com.zeynelerdi.mackolik.ui.livescore

import com.zeynelerdi.mackolik.ui.common.base.BaseNavigator
import com.zeynelerdi.mackolik.ui.livescore.model.response.Matches

interface LiveScoreNavigator : BaseNavigator{
    fun onMatchClick(match: Matches)
}