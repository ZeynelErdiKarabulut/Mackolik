package com.zeynelerdi.mackolik.ui.news.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News (
    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String?,
    @SerializedName("description") var description : String?,
    @SerializedName("date") var date : String?,
    @SerializedName("link") var link : String?,
    @SerializedName("picUrl") var picUrl : String?
): Parcelable