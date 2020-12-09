package com.zeynelerdi.mackolik.ui.common.components.spinner

import android.text.Spannable

interface SpinnerTextFormatter {
    fun format(item: Any): Spannable?
}