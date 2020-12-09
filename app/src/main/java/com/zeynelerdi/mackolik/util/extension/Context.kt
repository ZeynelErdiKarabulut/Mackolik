package com.zeynelerdi.mackolik.util.extension

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import androidx.core.content.ContextCompat

/**
 * The extension brings the screen width. Return type is integer
 */
val Context?.screenWidth: Int
    get() {
        if (this.isNull()) return 0
        val windowManager = this!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.let {
            val dm = DisplayMetrics()
            it.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }
    }

/**
 * The extension brings the screen height. Return type is integer
 */
val Context?.screenHeight: Int
    get() {
        if (this.isNull()) return 0
        val windowManager = this!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.let {
            val dm = DisplayMetrics()
            it.defaultDisplay.getMetrics(dm)
            return dm.heightPixels
        }
    }


fun Activity?.setWindowFlag(on: Boolean) {
    this?.let {
        val win = it.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags =
                winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        win.attributes = winParams
    }
}

fun Activity?.setNavController(colorId: Int, isHide: Boolean, isDarkTheme: Boolean) {
    this?.window?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isHide) {
                it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            } else {
                if (Build.VERSION.SDK_INT >= 27 && !isDarkTheme) {
                    it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }
            }

            it.navigationBarColor = ContextCompat.getColor(it.context, colorId)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.navigationBarDividerColor = ContextCompat.getColor(it.context, colorId)
            }
        }
    }
}

fun Activity?.setNavController(typedValue: TypedValue, isHide: Boolean, isDarkTheme: Boolean) {
    this?.window?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isHide) {
                it.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } else {
                if (Build.VERSION.SDK_INT >= 27 && !isDarkTheme) {
                    it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }
            }

            it.navigationBarColor = ContextCompat.getColor(it.context, typedValue.resourceId)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.navigationBarDividerColor =
                    ContextCompat.getColor(it.context, typedValue.resourceId)
            }
        }
    }
}

fun Context.getNavigationbarHeight(): Int {
    val result = 0
    val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
    val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)

    if (!hasMenuKey && !hasBackKey) { //The device has a navigation bar
        val orientation: Int = resources.configuration.orientation
        val resourceId: Int
        resourceId = if (isTablet()) {
            resources.getIdentifier(
                if (orientation == Configuration.ORIENTATION_PORTRAIT) "navigation_bar_height" else "navigation_bar_height_landscape",
                "dimen",
                "android"
            )
        } else {
            resources.getIdentifier(
                if (orientation == Configuration.ORIENTATION_PORTRAIT) "navigation_bar_height" else "navigation_bar_width",
                "dimen",
                "android"
            )
        }
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId)
        }
    }
    return result
}

fun Context?.loadJSONFromAsset(jsonFileName: String): String? {
    this.notNull {
        val manager = it.assets
        val `is` = manager.open(jsonFileName)

        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        return String(buffer, Charsets.UTF_8)
    }

    return null
}