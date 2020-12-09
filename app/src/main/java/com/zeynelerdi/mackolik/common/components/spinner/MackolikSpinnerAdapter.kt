package com.zeynelerdi.mackolik.ui.common.components.spinner

class MackolikSpinnerAdapter internal constructor(
    private val items: List<Any>,
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
        return items.size - 1
    }

    override fun getItem(position: Int): Any {
        return if (position >= selectedIndex) {
            items[position + 1]
        } else {
            items[position]
        }
    }

    override fun getItemInDataset(position: Int): Any {
        return items[position]
    }
}