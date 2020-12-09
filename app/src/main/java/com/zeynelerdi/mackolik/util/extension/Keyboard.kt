package com.zeynelerdi.mackolik.util.extension

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.orhanobut.logger.Logger

/**Examples**/
/**
 * context?.hideKeyboard()
 * context!!.hideKeyboard()
 *
 * if context is null, catch runs.
 */
fun Context?.hideKeyboard() {
    try {
        val inputMethodManager =
            this!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            (this as Activity).currentFocus!!.windowToken,
            0
        )
    } catch (e: Exception) {
        if (e.message != null)
            Logger.d("KeyboardError", e.message)
    }

}

/**Examples**/
/**
 * context?.hideKeyboard(view)
 * context!!.hideKeyboard(view)
 *
 * if context is null, catch runs.
 */
fun Context?.hideKeyboard(v: View) {
    try {
        val inputMethodManager =
            this!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    } catch (e: Exception) {
        if (e.message != null)
            Logger.d("KeyboardError", e.message)
    }

}

/**Examples**/
/**
 * context?.showKeyboard(view)
 * context!!.showKeyboard(view)
 *
 * if context is null, catch runs.
 */
fun Context?.showKeyboard(view: View) {
    try {
        val inputMethodManager =
            this!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    } catch (e: Exception) {
        Logger.d("KeyboardError", e.message)
    }

}

/**Examples**/
/**
 * context?.showKeyboard()
 * context!!.showKeyboard()
 *
 * if context is null, catch runs.
 */
fun Context?.showKeyboard() {
    try {
        var imm: InputMethodManager =
            this!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    } catch (e: Exception) {
        Logger.d("KeyboardError", e.message)
    }
}

fun Context?.isKeyboardShowing(view: View): Boolean {
    return try {
        val keyboard = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(view.windowToken, 0)
        keyboard.isActive
    } catch (ex: Exception) {
        Log.e("keyboardHide", "cannot hide keyboard", ex)
        false
    }

}