package com.zeynelerdi.mackolik.ui.common.components.spinner

import android.widget.ListAdapter

class MackolikSpinnerAdapterWrapper internal constructor(
    private val baseAdapter: ListAdapter,
    textColor: Int,
    backgroundSelector: Int,
    spinnerTextFormatter: SpinnerTextFormatter?,
    horizontalAlignment: PopUpTextAlignment?
) : MackolikSpinnerBaseAdapter(
    textColor,
    backgroundSelector,
    spinnerTextFormatter,
    horizontalAlignment
) {
    override fun getCount(): Int {
        return baseAdapter.count - 1
    }

    override fun getItem(position: Int): Any {
        return baseAdapter.getItem(if (position >= selectedIndex) position + 1 else position)
    }

    override fun getItemInDataset(position: Int): Any {
        return baseAdapter.getItem(position)
    }
}