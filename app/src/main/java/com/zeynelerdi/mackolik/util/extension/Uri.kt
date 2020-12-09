package com.zeynelerdi.mackolik.util.extension

import android.content.ContentResolver
import android.content.Context
import android.net.Uri

fun Context.getUri(raw: Int): Uri {
    return Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.packageName + "/" + raw
    )
}