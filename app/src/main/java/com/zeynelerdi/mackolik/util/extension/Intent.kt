package com.zeynelerdi.mackolik.util.extension

import android.content.Context
import android.content.Intent
import android.net.Uri

/**Example**/
/**
 * context.callThePhone("5554443322")
 * context.callThePhone("555 444 33 22")
 * context.callThePhone("05554443322")
 * context.callThePhone("0 555 444 33 22")
 * context.callThePhone("+905554443322")
 * context.callThePhone("+90 555 444 33 22")
 *
 * The context variable can be null
 */
fun Context?.callThePhone(phone: String) {
    if (this.isNull()) return
    val uri = Uri.parse("tel:$phone")
    val it = Intent(Intent.ACTION_DIAL, uri)
    this?.startActivity(it)
}

/**Example**/
/**
 *  context.sendEMail("loodos@loodos.com")
 * context.sendEMail("loodos@loodos.com.tr")
 *
 * The context variable can be null
 */
fun Context?.sendEMail(email: String) {
    if (this.isNull()) return
    val uri = Uri.parse("mailto:$email")
    val it = Intent(Intent.ACTION_SENDTO, uri)
    this?.startActivity(it)
}
/**Example**/
/**
 * context.sendSMS("5554443322")
 * context.sendSMS("555 444 33 22")
 * context.sendSMS("05554443322")
 * context.sendSMS("0 555 444 33 22")
 * context.sendSMS("+905554443322")
 * context.sendSMS("+90 555 444 33 22")
 *
 *  The context variable can be null
 */
fun Context?.sendSMS(phone: String) {
    if (this.isNull()) return
    val uri = Uri.parse("smsto:$phone")
    val it = Intent(Intent.ACTION_SENDTO, uri)
    this?.startActivity(it)
}