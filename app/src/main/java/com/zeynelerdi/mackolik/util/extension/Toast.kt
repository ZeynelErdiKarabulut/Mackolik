package com.zeynelerdi.mackolik.util.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**EXAMPLES**/
/**
 * context.showToast("toast message text") -> toast message duration is default
 * context.showToast("toast message text",Toast.LENGTH_LONG) -> toast message duration isn't default, it's long
 */
fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

/**EXAMPLES**/
/**
 * context.showToast(R.string.label) -> toast message duration is default
 * context.showToast("toast message text",Toast.LENGTH_LONG) -> toast message duration isn't default, it's long
 */
fun Context.showToast(@StringRes messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageRes, duration).show()
}

/**EXAMPLES**/
/**
 * fragment.showToast("toast message text") -> toast message duration is default
 * fragment.showToast("toast message text",Toast.LENGTH_LONG) -> toast message duration isn't default, it's long
 */
fun Fragment.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, text, duration).show()
}

/**EXAMPLES**/
/**
 * fragment.showToast(R.string.label) -> toast message duration is default
 * fragment.showToast("toast message text",Toast.LENGTH_LONG) -> toast message duration isn't default, it's long
 */
fun Fragment.showToast(@StringRes messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, messageRes, duration).show()
}
