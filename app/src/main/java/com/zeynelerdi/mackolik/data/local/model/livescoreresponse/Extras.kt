package com.zeynelerdi.mackolik.ui.livescore.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Extras(
	@SerializedName("iddaa_code") var iddaa_code: Int?,
	@SerializedName("iddaa_live") var iddaa_live: Boolean?
): Parcelable