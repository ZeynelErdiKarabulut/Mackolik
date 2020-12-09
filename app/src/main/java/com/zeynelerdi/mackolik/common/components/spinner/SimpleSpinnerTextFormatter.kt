package com.zeynelerdi.mackolik.ui.common.components.spinner

import android.text.Spannable
import android.text.SpannableString

class SimpleSpinnerTextFormatter : SpinnerTextFormatter {
    override fun format(item: Any): Spannable? {
        return SpannableString(item.toString())
    }
}