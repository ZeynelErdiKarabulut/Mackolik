package com.zeynelerdi.mackolik.ui.livescore.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamDto(
	@SerializedName("id") var id: Int,
	@SerializedName("uuid") var uuid: String?,
	@SerializedName("name") var name: String?,
	@SerializedName("tla_name") var tla_name: String?,
	@SerializedName("display_name") var display_name: String?
): Parcelable