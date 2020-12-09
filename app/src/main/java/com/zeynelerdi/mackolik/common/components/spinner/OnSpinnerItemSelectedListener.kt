package com.zeynelerdi.mackolik.ui.common.components.spinner

import android.view.View

interface OnSpinnerItemSelectedListener {
    fun onItemSelected(parent: MackolikSpinner?, view: View?, position: Int, id: Long)
}