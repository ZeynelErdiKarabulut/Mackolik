package com.zeynelerdi.mackolik.ui.livescore.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LiveScoreResponse(
	@SerializedName("name") var name: String?,
	@SerializedName("format") var format: String?,
	@SerializedName("date") var date: String?,
	@SerializedName("matches") var matches: List<Matches?>?,
	@SerializedName("groups") var groups: List<String?>?,
	@SerializedName("displayRound") var displayRound: Boolean?
): Parcelable