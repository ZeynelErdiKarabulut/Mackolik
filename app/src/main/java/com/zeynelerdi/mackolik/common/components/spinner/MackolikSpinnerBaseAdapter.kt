package com.zeynelerdi.mackolik.ui.common.components.spinner

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.zeynelerdi.mackolik.R

abstract class MackolikSpinnerBaseAdapter internal constructor(
    private val textColor: Int,
    private val backgroundSelector: Int,
    private val spinnerTextFormatter: SpinnerTextFormatter?,
    private val horizontalAlignment: PopUpTextAlignment?
) : BaseAdapter() {
    var selectedIndex = 0
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var mConvertView = convertView
        val textView: TextView
        if (mConvertView == null) {
            mConvertView = View.inflate(parent.context, R.layout.spinner_list_item, null)
            textView = mConvertView.findViewById(R.id.text_view_spinner)
            textView.background = ContextCompat.getDrawable(parent.context, backgroundSelector)
            mConvertView.tag = ViewHolder(textView)
        } else {
            textView = (mConvertView.tag as ViewHolder).textView
        }
        textView.text = spinnerTextFormatter?.format(getItem(position))
        textView.setTextColor(textColor)
        setTextHorizontalAlignment(textView)
        return mConvertView
    }

    private fun setTextHorizontalAlignment(textView: TextView) {
        when (horizontalAlignment) {
            PopUpTextAlignment.START -> textView.gravity = Gravity.START
            PopUpTextAlignment.END -> textView.gravity = Gravity.END
            PopUpTextAlignment.CENTER -> textView.gravity = Gravity.CENTER
        }
    }

    abstract fun getItemInDataset(position: Int): Any
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    abstract override fun getItem(position: Int): Any
    abstract override fun getCount(): Int
    internal class ViewHolder(var textView: TextView)
}