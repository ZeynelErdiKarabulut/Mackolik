package com.zeynelerdi.mackolik.ui.livescore.model.data

import android.os.Parcelable
import com.zeynelerdi.mackolik.ui.livescore.model.response.Matches
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LiveScoreData(
	var id: Int,
	var matches: Matches?,
	var title: String = ""
): Parcelable