package com.leo.itemlayout

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.leo.itemlayout.config.*
import com.leo.itemlayout.item.AbsItemFactory
import com.leo.itemlayout.item.AbsItemView
import com.leo.itemlayout.item.IconItemView
import com.leo.itemlayout.item.ItemFactory

class BaseItemLayout : LinearLayout {

    private lateinit var factory: AbsItemFactory
    private lateinit var attributeCreator: AttributeCreator
    private var viewList = arrayListOf<View>()
    private var lineColor: Int = 0
    private var kBackgroundColor: Int = 0
    private var textSize: Float = 0f
    private var textColor: Int = 0
    private var iconMarginLeft: Int = 0
    private var iconTextMargin: Int = 0
    private var arrowMarginRight: Int = 0
    private var itemHeight: Int = 0
    private var rightTextSize: Int = 0
    private var rightTextColor: Int = 0
    private var rightTextMargin: Int = 0

    private var itemClickListener: OnItemClickListener? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        initCustomerAttrs(context, attrs)
        initItem()
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun initCustomerAttrs(context: Context?, attrs: AttributeSet?) {

        val a = context?.obtainStyledAttributes(attrs, R.styleable.ItemAttrs) ?: return

        lineColor = a.getColor(R.styleable.ItemAttrs_item_line_color, LINE_COLOR)
        kBackgroundColor = a.getResourceId(
            R.styleable.ItemAttrs_item_background_color, BACKGROUND_COLOR
        )
        textSize = a.getFloat(R.styleable.ItemAttrs_item_text_size, 0f)
        textColor = a.getColor(R.styleable.ItemAttrs_item_text_color, 0)
        iconMarginLeft = a.getInt(R.styleable.ItemAttrs_item_icon_margin_left, ICON_MARGIN_LEFT)
        iconTextMargin = a.getInt(R.styleable.ItemAttrs_item_icon_text_margin, ICON_TEXT_MARGIN)
        arrowMarginRight = a.getInt(R.styleable.ItemAttrs_item_margin_right, ARROW_MARGIN_RIGHT)
        itemHeight = a.getInt(R.styleable.ItemAttrs_item_item_height, ITEM_HEIGHT)
        rightTextSize = a.getInt(R.styleable.ItemAttrs_item_right_text_size, RIGHT_TEXT_SIZE)
        rightTextColor = a.getColor(R.styleable.ItemAttrs_item_right_text_color, RIGHT_TEXT_COLOR)
        rightTextMargin = a.getInt(R.styleable.ItemAttrs_item_right_text_margin, RIGHT_TEXT_MARGIN)

        a.recycle()
    }

    private fun initItem() {
        orientation = VERTICAL
        factory = ItemFactory(context)
        layoutTransition = LayoutTransition()
        addView(IconItemView(context))
    }

    fun setAttributeCreator(attrs: AttributeCreator) = this.apply {

        this.attributeCreator = attrs

        if (attributeCreator.lineColor == 0) {
            attributeCreator.setLineColor(lineColor)
        }
        if (attributeCreator.itemBackgroundColor == 0) {
            attributeCreator.setItemBackgroundColor(kBackgroundColor)
        }
        if (textSize > 0 && textColor != 0) {
            // xml中定义了标题字体大小和颜色则重写
            attributeCreator.setTitleTextStyle(TextStyle(textSize, textColor))
        }
        if (attributeCreator.startMargin == 0) {
            attributeCreator.setStartMargin(iconMarginLeft)
        }
        if (attributeCreator.itemViewHeightPx == 0) {
            attributeCreator.setItemViewHeight(itemHeight)
        }
        createItems()
    }

    private fun createItems() {

        if (attributeCreator.titleTextList == null || attributeCreator.titleTextList?.size == 0) {
            throw RuntimeException("valueList  is null")
        }

        attributeCreator.titleTextList?.forEachIndexed { index, _ ->
            attributeCreator.setItemPosition(index)
            val modeArray = attributeCreator.modeArray
            val mode = modeArray.get(index)

            if (mode == ItemMode.OTHER) {
                // TODO 2019/11/14 Leo other View
                if (attributeCreator.otherItemView != null) {
                    addItem(attributeCreator.otherItemView!!, index)
                }
            } else {
                val itemView = factory.createItem<AbsItemView>(mode, attributeCreator)
                addItem(itemView, index)
            }
        }
    }

    private fun addItem(view: View, index: Int) {
        addView(view)

        val marginTop = attributeCreator.marginTopArray.get(index)

        if (marginTop > 0) {
            val params = view.layoutParams as LayoutParams
            params.topMargin = marginTop
            view.layoutParams = params
        }

        view.setOnClickListener { itemClickListener?.onItemClick(index) }
        viewList.add(view)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) = this.let {
        this.itemClickListener = listener
        this
    }

    fun getChildViewList() = viewList

    interface OnItemClickListener {
        fun onItemClick(index: Int)
    }
}