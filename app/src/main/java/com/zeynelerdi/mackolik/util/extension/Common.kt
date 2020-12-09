package com.zeynelerdi.mackolik.util.extension

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.zeynelerdi.mackolik.BuildConfig

val Context.versionOS: String
    get() = Build.VERSION.RELEASE

val Context.versionApp: String
    get() = BuildConfig.VERSION_NAME

val Context.OS: String
    get() = "android"

fun Context.isTablet(): Boolean {
    return ((this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}