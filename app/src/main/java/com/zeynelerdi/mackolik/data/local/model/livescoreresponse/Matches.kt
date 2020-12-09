package com.zeynelerdi.mackolik.ui.livescore.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Matches(
	@SerializedName("id") var id: Int,
	@SerializedName("date_time_utc") var date_time_utc: String?,
	@SerializedName("match_time") var match_time: String?,
	@SerializedName("status") var status: String?,
	@SerializedName("fts_A") var fts_A: Int?,
	@SerializedName("fts_B") var fts_B: Int?,
	@SerializedName("hts_A") var hts_A: Int?,
	@SerializedName("hts_B") var hts_B: Int?,
	@SerializedName("team_A") var team_A: TeamDto?,
	@SerializedName("team_B") var team_B: TeamDto?,
	@SerializedName("extras") var extras: Extras?
): Parcelable