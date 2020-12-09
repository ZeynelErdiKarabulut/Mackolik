package com.zeynelerdi.mackolik.ui.news.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse (
    @SerializedName("news") var news : List<News?>?
): Parcelable