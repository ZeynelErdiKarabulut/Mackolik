package com.zeynelerdi.mackolik.util.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class RecyclerViewMarginDecoration(
    private val marginTop: Int,
    private val marginStart: Int,
    private val marginBottom: Int,
    private val marginEnd: Int
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val position: Int = parent.getChildLayoutPosition(view)
        val size: Int = parent.childCount

        if (position != 0) {
            outRect.top = marginTop
        }

        if (position != size-1){
            outRect.bottom = marginBottom
        }

        outRect.left = marginStart
        outRect.right = marginEnd
    }
}