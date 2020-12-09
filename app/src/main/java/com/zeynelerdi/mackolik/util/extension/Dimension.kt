package com.zeynelerdi.mackolik.util.extension

import android.content.Context
import android.view.View
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment

const val LDPI: Int = android.util.DisplayMetrics.DENSITY_LOW
const val MDPI: Int = android.util.DisplayMetrics.DENSITY_MEDIUM
const val HDPI: Int = android.util.DisplayMetrics.DENSITY_HIGH

//May not be available on older Android versions
const val TVDPI: Int = 213
const val XHDPI: Int = 320
const val XXHDPI: Int = 480
const val XXXHDPI: Int = 640

const val MAXDPI: Int = 0xfffe

//returns dip(dp) dimension value in pixels
fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Context.dip(value: Float): Int = (value * resources.displayMetrics.density).toInt()

//return sp dimension value in pixels
fun Context.sp(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Context.sp(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

//converts px value into dip or sp
fun Context.px2dip(px: Int): Float = px.toFloat() / resources.displayMetrics.density

fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

//the same for the views
fun View.dip(value: Int): Int = context.dip(value)

fun View.dip(value: Float): Int = context.dip(value)
fun View.sp(value: Int): Int = context.sp(value)
fun View.sp(value: Float): Int = context.sp(value)
fun View.px2dip(px: Int): Float = context.px2dip(px)
fun View.px2sp(px: Int): Float = context.px2sp(px)
fun View.dimen(@DimenRes resource: Int): Int = context.dimen(resource)

//the same for Fragments
fun Fragment.dip(value: Int): Int = activity?.dip(value)!!

fun Fragment.dip(value: Float): Int = activity?.dip(value)!!
fun Fragment.sp(value: Int): Int = activity?.sp(value)!!
fun Fragment.sp(value: Float): Int = activity?.sp(value)!!
fun Fragment.px2dip(px: Int): Float = activity?.px2dip(px)!!
fun Fragment.px2sp(px: Int): Float = activity?.px2sp(px)!!
fun Fragment.dimen(@DimenRes resource: Int): Int = activity?.dimen(resource)!!