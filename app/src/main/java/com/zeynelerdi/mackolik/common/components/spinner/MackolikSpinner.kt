package com.zeynelerdi.mackolik.ui.common.components.spinner

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ListAdapter
import android.widget.ListPopupWindow
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.zeynelerdi.mackolik.R

class MackolikSpinner : AppCompatTextView {
    var selectedItemIndex: Int = 0
    private var arrowDrawable: Drawable? = null
    private var popupWindow: ListPopupWindow? = null
    private var adapter: MackolikSpinnerBaseAdapter? = null
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemSelectedListener: OnItemSelectedListener? = null

    var onSpinnerItemSelectedListener: OnSpinnerItemSelectedListener? = null

    var isArrowHidden = false
        private set
    private var mTextColor = 0
    private var mPopupTextColor = 0
    private var backgroundSelector = 0
    private var popupBackgroundSelector = 0
    private var arrowDrawableTint = 0
    private var displayHeight = 0
    private var parentVerticalOffset = 0
        get() {
            if (field > 0) {
                return field
            }
            val locationOnScreen = IntArray(2)
            getLocationOnScreen(locationOnScreen)
            return locationOnScreen[VERTICAL_OFFSET].also { field = it }
        }

    @DrawableRes
    private var arrowDrawableResId = 0
    private var spinnerTextFormatter: SpinnerTextFormatter = SimpleSpinnerTextFormatter()
    private var selectedTextFormatter: SpinnerTextFormatter? = SimpleSpinnerTextFormatter()
    var popUpTextAlignment: PopUpTextAlignment? = null
        private set
    private var arrowAnimator: ObjectAnimator? = null

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState())
        bundle.putInt(SELECTED_INDEX, selectedItemIndex)
        bundle.putBoolean(IS_ARROW_HIDDEN, isArrowHidden)
        bundle.putInt(ARROW_DRAWABLE_RES_ID, arrowDrawableResId)
        if (popupWindow != null) {
            bundle.putBoolean(IS_POPUP_SHOWING, popupWindow?.isShowing!!)
        }
        return bundle
    }

    override fun onRestoreInstanceState(savedState: Parcelable) {
        var savedState: Parcelable? = savedState
        if (savedState is Bundle) {
            val bundle = savedState
            selectedItemIndex = bundle.getInt(SELECTED_INDEX)
            if (adapter != null) {
                setTextInternal(
                    adapter?.getItemInDataset(
                        selectedItemIndex
                    )?.let {
                        selectedTextFormatter?.format(
                            it
                        ).toString()
                    }
                )
                adapter!!.selectedIndex = selectedItemIndex
            }
            if (bundle.getBoolean(IS_POPUP_SHOWING)) {
                if (popupWindow != null) {
                    // Post the show request into the looper to avoid bad token exception
                    post { showDropDown() }
                }
            }
            isArrowHidden = bundle.getBoolean(IS_ARROW_HIDDEN, false)
            arrowDrawableResId = bundle.getInt(ARROW_DRAWABLE_RES_ID)
            savedState = bundle.getParcelable(INSTANCE_STATE)
        }
        super.onRestoreInstanceState(savedState)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val resources = resources
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MackolikSpinner)
        val defaultPadding = resources.getDimensionPixelSize(R.dimen.one_and_a_half_grid_unit)
        gravity = Gravity.CENTER_VERTICAL or Gravity.START
        setPadding(
            resources.getDimensionPixelSize(R.dimen.three_grid_unit),
            defaultPadding,
            defaultPadding,
            defaultPadding
        )
        isClickable = true
        backgroundSelector = typedArray.getResourceId(
            R.styleable.MackolikSpinner_backgroundSelector,
            R.drawable.spinner_selector
        )
        popupBackgroundSelector = typedArray.getResourceId(
            R.styleable.MackolikSpinner_spinnerBackgroundSelector,
            R.drawable.spinner_popup_selector
        )
        setBackgroundResource(backgroundSelector)
        mTextColor = R.color.fenerbahce_lacivert

        mPopupTextColor = R.color.fenerbahce_yellow


        setTextColor(mTextColor)
        popupWindow = ListPopupWindow(context)
        popupWindow?.let {
            it.width = ListPopupWindow.WRAP_CONTENT
            it.height = ListPopupWindow.WRAP_CONTENT
            it.setOnItemClickListener { parent, view, position, id -> // The selected item is not displayed within the list, so when the selected position is equal to
                // the one of the currently selected item it gets shifted to the next item.
                var mPosition = position
                if (mPosition >= selectedItemIndex && mPosition < adapter!!.count) {
                    mPosition++
                }
                selectedItemIndex = mPosition
                if (onSpinnerItemSelectedListener != null) {
                    onSpinnerItemSelectedListener!!.onItemSelected(this@MackolikSpinner, view, mPosition, id)
                }
                if (onItemClickListener != null) {
                    onItemClickListener!!.onItemClick(parent, view, mPosition, id)
                }
                if (onItemSelectedListener != null) {
                    onItemSelectedListener!!.onItemSelected(parent, view, mPosition, id)
                }
                adapter?.selectedIndex = mPosition
                setTextInternal(adapter?.getItemInDataset(mPosition))
                dismissDropDown()
            }
            it.isModal = true
            it.setOnDismissListener {
                if (!isArrowHidden) {
                    animateArrow(false)
                }
            }
        }

        isArrowHidden = typedArray.getBoolean(R.styleable.MackolikSpinner_hideArrow, false)
        arrowDrawableTint = typedArray.getColor(
            R.styleable.MackolikSpinner_arrowTint,
            ContextCompat.getColor(context, R.color.black)
        )
        arrowDrawableResId =
            typedArray.getResourceId(R.styleable.MackolikSpinner_arrowDrawable, R.drawable.arrow)
        popUpTextAlignment = PopUpTextAlignment.fromId(
            typedArray.getInt(
                R.styleable.MackolikSpinner_popupTextAlignment,
                PopUpTextAlignment.CENTER.ordinal
            )
        )
        val entries = typedArray.getTextArray(R.styleable.MackolikSpinner_entries)
        if (entries != null) {
            attachDataSource(listOf(*entries))
        }
        typedArray.recycle()
        measureDisplayHeight()
    }

    private fun measureDisplayHeight() {
        displayHeight = context.resources.displayMetrics.heightPixels
    }

    override fun onDetachedFromWindow() {
        if (arrowAnimator != null) {
            arrowAnimator!!.cancel()
        }
        super.onDetachedFromWindow()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            onVisibilityChanged(this, visibility)
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        arrowDrawable = initArrowDrawable(arrowDrawableTint)
        setArrowDrawableOrHide(arrowDrawable)
    }

    private fun initArrowDrawable(drawableTint: Int): Drawable? {
        if (arrowDrawableResId == 0) return null
        var drawable = ContextCompat.getDrawable(context, arrowDrawableResId)
        if (drawable != null) {
            // Gets a copy of this drawable as this is going to be mutated by the animator
            drawable = DrawableCompat.wrap(drawable).mutate()
            if (drawableTint != Int.MAX_VALUE && drawableTint != 0) {
                DrawableCompat.setTint(drawable, drawableTint)
            }
        }
        return drawable
    }

    private fun setArrowDrawableOrHide(drawable: Drawable?) {
        if (!isArrowHidden && drawable != null) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        }
    }

    private fun getDefaultTextColor(context: Context): Int {
        val typedValue = TypedValue()
        context.theme
//            .resolveAttribute(android.R.attr.textColorPrimary, typedValue, true)

            .resolveAttribute(R.color.color_on_secondary, typedValue, true)
        val typedArray = context.obtainStyledAttributes(
            typedValue.data,
            intArrayOf(android.R.attr.textColorPrimary)
        )
        val defaultTextColor = typedArray.getColor(0, Color.BLACK)
        typedArray.recycle()
        return defaultTextColor
    }

    fun getItemAtPosition(position: Int): Any {
        return adapter!!.getItemInDataset(position)
    }

    val selectedItem: Any
        get() = adapter!!.getItemInDataset(selectedItemIndex)

    fun getSelectedIndex(): Int {
        return selectedItemIndex
    }

    fun setArrowDrawable(@DrawableRes @ColorRes drawableId: Int) {
        arrowDrawableResId = drawableId
        arrowDrawable = initArrowDrawable(R.drawable.arrow)
        setArrowDrawableOrHide(arrowDrawable)
    }

    fun setArrowDrawable(drawable: Drawable?) {
        arrowDrawable = drawable
        setArrowDrawableOrHide(arrowDrawable)
    }

    private fun setTextInternal(item: Any?) {
        text = if (selectedTextFormatter != null) {
            item?.let { selectedTextFormatter?.format(it) }
        } else {
            item.toString()
        }
    }

    /**
     * Set the default spinner item using its index
     *
     * @param position the item's position
     */
    public fun setSelectedIndex(position: Int) {
        if (adapter != null) {
            if (position >= 0 && position <= adapter!!.count) {
                adapter!!.selectedIndex = position
                selectedItemIndex = position
                setTextInternal(
                    selectedTextFormatter!!.format(adapter!!.getItemInDataset(position)).toString()
                )
            } else {
                throw IllegalArgumentException("Position must be lower than adapter count!")
            }
        }
    }

    @Deprecated("use setOnSpinnerItemSelectedListener instead.")
    fun addOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    @Deprecated("use setOnSpinnerItemSelectedListener instead.")
    fun setOnItemSelectedListener(onItemSelectedListener: OnItemSelectedListener?) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    fun attachDataSource(list: List<Any>) {
        adapter = MackolikSpinnerAdapter(
            list,
            mPopupTextColor,
            popupBackgroundSelector,
            spinnerTextFormatter,
            popUpTextAlignment
        )
        setAdapterInternal(adapter)
    }

    fun setAdapter(adapter: ListAdapter?) {
        this.adapter = adapter?.let {
            MackolikSpinnerAdapterWrapper(
                it, mPopupTextColor, popupBackgroundSelector,
                spinnerTextFormatter, popUpTextAlignment
            )
        }
        setAdapterInternal(this.adapter)
    }

    private fun setAdapterInternal(adapter: MackolikSpinnerBaseAdapter?) {
        if (adapter!!.count >= 0) {
            // If the adapter needs to be set again, ensure to reset the selected index as well
            selectedItemIndex = 0
            popupWindow?.setAdapter(adapter)
            setTextInternal(adapter.getItemInDataset(selectedItemIndex))
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isEnabled && event.action == MotionEvent.ACTION_UP) {
            if (!popupWindow?.isShowing!! && adapter?.count!! > 0) {
                showDropDown()
            } else {
                dismissDropDown()
            }
        }
        return super.onTouchEvent(event)
    }

    private fun animateArrow(shouldRotateUp: Boolean) {
        val start = if (shouldRotateUp) 0 else MAX_LEVEL
        val end = if (shouldRotateUp) MAX_LEVEL else 0
        arrowDrawable?.let {
            arrowAnimator = ObjectAnimator.ofInt(it, "level", start, end)
            arrowAnimator?.interpolator = LinearOutSlowInInterpolator()
            arrowAnimator?.start()
        }
    }

    fun dismissDropDown() {
        if (popupWindow?.isShowing!!) {
            if (!isArrowHidden) {
                animateArrow(false)
            }
            popupWindow?.dismiss()
        }
    }

    fun showDropDown() {
        if (!popupWindow?.isShowing!!) {
            if (!isArrowHidden) {
                animateArrow(true)
            }

            popupWindow?.let { it ->
                it.anchorView = this
                it.listView?.let {
                    it.isVerticalScrollBarEnabled = false
                    it.isHorizontalScrollBarEnabled = false
                    it.isVerticalFadingEdgeEnabled = false
                    it.isHorizontalFadingEdgeEnabled = false
                }
                it.show()
            }
        }
    }

    private val popUpHeight: Int
        get() = verticalSpaceBelow().coerceAtLeast(verticalSpaceAbove())

    private fun verticalSpaceAbove(): Int {
        return parentVerticalOffset
    }

    private fun verticalSpaceBelow(): Int {
        return displayHeight - parentVerticalOffset - measuredHeight
    }

    fun setTintColor(@ColorRes resId: Int) {
        if (arrowDrawable != null && !isArrowHidden) {
            DrawableCompat.setTint(arrowDrawable!!, ContextCompat.getColor(context, resId))
        }
    }

    fun setArrowTintColor(resolvedColor: Int) {
        if (arrowDrawable != null && !isArrowHidden) {
            DrawableCompat.setTint(arrowDrawable!!, resolvedColor)
        }
    }

    fun hideArrow() {
        isArrowHidden = true
        setArrowDrawableOrHide(arrowDrawable)
    }

    fun showArrow() {
        isArrowHidden = false
        setArrowDrawableOrHide(arrowDrawable)
    }

    fun setSpinnerTextFormatter(spinnerTextFormatter: SpinnerTextFormatter) {
        this.spinnerTextFormatter = spinnerTextFormatter
    }

    fun setSelectedTextFormatter(textFormatter: SpinnerTextFormatter?) {
        selectedTextFormatter = textFormatter
    }

    fun performItemClick(position: Int, showDropdown: Boolean) {


        if (showDropdown) showDropDown()
        setSelectedIndex(position)
    }

    /**
     * only applicable when popup is shown .
     *
     * @param view
     * @param position
     * @param id
     */
    fun performItemClick(view: View?, position: Int, id: Int) {

        showDropDown()
        val listView = popupWindow?.listView
        listView?.performItemClick(view, position, id.toLong())
    }

    companion object {
        private const val MAX_LEVEL = 10000
        private const val VERTICAL_OFFSET = 1
        private const val INSTANCE_STATE = "instance_state"
        private const val SELECTED_INDEX = "selected_index"
        private const val IS_POPUP_SHOWING = "is_popup_showing"
        private const val IS_ARROW_HIDDEN = "is_arrow_hidden"
        private const val ARROW_DRAWABLE_RES_ID = "arrow_drawable_res_id"
    }
}