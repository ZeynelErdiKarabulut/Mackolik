package com.zeynelerdi.mackolik.util.extension

import java.util.*

/**Examples**/
/**
 * var time = 1571605729789L
 *
 * time.toDateTime() -> return as Date
 *
 * if it is null, return null
 */
fun Long?.toDateTime(): Date? = if (this.isNull()) null else Date(this!!)