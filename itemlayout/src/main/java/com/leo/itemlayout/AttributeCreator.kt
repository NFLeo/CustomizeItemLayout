package com.leo.itemlayout

import android.util.SparseArray
import android.util.SparseIntArray
import android.view.View
import com.leo.itemlayout.config.*

/**
 * desc：构造属性</br>
 * time: 2019/11/5-13:51</br>
 * author：Leo </br>
 * since V 1.0.0 </br>
 */
class AttributeCreator {

    // item 的模式
    val modeArray = SparseArray<ItemMode>()
    // 每一个 item 与 item 之间的 marginTop 的大小
    val marginTopArray = SparseIntArray()
    var startMargin: Int = ITEM_MARGIN
    var endMargin: Int = ITEM_MARGIN

    /**
     * 单个Item
     */
    var itemPosition: Int = 0                               // 当前条目所在位置
    var itemViewHeightPx: Int = ITEM_HEIGHT                 // 每个条目的高度
    var itemBackgroundColor: Int = R.color.transparent
    var itemNeedBorderless = true

    /**
     * 左侧图标
     */
    var leftIconWidthPx: Int = ICON_HEIGHT
    var leftIconHeightPx: Int = ICON_WIDTH
    var leftResourceIds: MutableList<Int>? = null

    /**
     * 标题文本
     */
    var titleMarginIcon: Int = ITEM_MARGIN                   // 标题与图标的margin
    var titleTextStyle: TextStyle? = null
    var titleTextList: MutableList<String>? = null

    /**
     * 分割线
     */
    var lineColor = 0xeeeeee
    var lineHeight = 1
    var isHideDivider = false
    // 分割线距离左边距离
    val lineMarginLeftArray = SparseIntArray()

    /**
     * 右侧图标
     */
    var rightIconWidthPx: Int = ICON_HEIGHT
    var rightIconHeightPx: Int = ICON_WIDTH
    var rightArrowResIds = SparseIntArray()

    /**
     * 右侧文本
     */
    var rightTextStyle: TextStyle? = null
    // 右边文本距离右边图标分隔距离
    var rightTextMarginToIcon = 0
    var rightTextList = SparseArray<String>()

    /**
     * 中间文本
     */
    var centerTextStyle: TextStyle? = null
    // 中间文本距离左边图标分隔距离
    var centerTextMarginToTitle = 0
    var centerTextMarginToArrow = 0
    var centerTextList = SparseArray<String>()

    /**
     * 其他自定义View
     */
    var otherItemView: View? = null

    /**
     * 设置每个Item都为同一种模式{@link ItemMode}
     */
    fun setItemMode(value: ItemMode) = this.run {

        if (titleTextList?.size ?: 0 <= 0) {
            throw RuntimeException("values is null")
        }

        titleTextList?.forEachIndexed { index, _ ->
            modeArray.put(index, value)
        }
        this
    }

    /**
     * 设置某位置上Item的模式{@link ItemMode}
     */
    fun setItemMode(index: Int, value: ItemMode) = this.run {
        modeArray.put(index, value)
        this
    }

    /**
     * 设置指定位置item与上个item的间距
     */
    fun setItemMarginTop(index: Int, marginTopPx: Int) = this.run {
        marginTopArray.put(index, marginTopPx)
        this
    }

    /**
     * 设置所有位置item与上个item的间距
     */
    fun setItemMarginTop(marginTopPx: Int) = this.run {

        if (titleTextList?.size ?: 0 <= 0) {
            throw RuntimeException("values is null")
        }

        titleTextList?.forEachIndexed { index, _ ->
            marginTopArray.put(index, marginTopPx)
        }
        this
    }

    /**
     * 设置单个Item的高度
     */
    fun setItemViewHeight(viewHeightPx: Int) = this.run {
        if (viewHeightPx > 0) itemViewHeightPx = viewHeightPx
        this
    }

    /**
     * 设置单个Item背景
     */
    fun setItemBackgroundColor(backgroundColor: Int) = this.run {
        itemBackgroundColor = backgroundColor
        this
    }

    /**
     * 点击Item，背景是否需要需要扩散动画
     */
    fun setItemNeedBorderless(needBorderless: Boolean) = this.run {
        itemNeedBorderless = needBorderless
        this
    }

    /**
     * 设置左侧图标的宽度
     */
    fun setIconWidth(iconWidthPx: Int) = this.run {
        if (iconWidthPx > 0) leftIconWidthPx = iconWidthPx
        this
    }

    /**
     * 设置左侧图标的高度
     */
    fun setIconHeight(iconHeightPx: Int) = this.run {
        if (iconHeightPx > 0) leftIconHeightPx = iconHeightPx
        this
    }

    /**
     * 设置左侧图标本地资源集合
     */
    fun setIconResourceIds(iconResIds: MutableList<Int>) = this.run {
        leftResourceIds = iconResIds
        this
    }

    /**
     * 内容与左边界距离
     */
    fun setStartMargin(marginStartPx: Int) = this.run {
        if (marginStartPx > 0) startMargin = marginStartPx
        this
    }

    /**
     * 内容与右边界距离
     */
    fun setEndMargin(marginEndPx: Int) = this.run {
        if (marginEndPx > 0) endMargin = marginEndPx
        this
    }

    /**
     * 当前item在整个组合view中的位置序号
     */
    fun setItemPosition(itemPos: Int) = this.run {
        itemPosition = itemPos
        this
    }

    /**
     * 设置标题与图标间距
     */
    fun setTitleMarginIcon(titleMarginIconPx: Int) = this.run {
        if (titleMarginIconPx > 0) titleMarginIcon = titleMarginIconPx
        this
    }

    /**
     * 设置标题文本文字属性
     */
    fun setTitleTextStyle(textStyle: TextStyle) = this.run {
        titleTextStyle = textStyle
        this
    }

    /**
     * 设置左侧图标本地资源集合
     */
    fun setTitleTextList(textList: MutableList<String>) = this.run {
        titleTextList = textList
        this
    }

    /**
     * 设置分割线颜色
     */
    fun setLineColor(lineColor: Int) = this.run {
        this.lineColor = lineColor
        this
    }

    /**
     * 设置是否隐藏分割线
     */
    fun setHideDivider(isHideDivider: Boolean) = this.run {
        this.isHideDivider = isHideDivider
        this
    }

    /**
     * 设置分割线高度
     */
    fun setLineHeight(lineHeightPx: Int) = this.run {
        this.lineHeight = lineHeightPx
        this
    }

    /**
     * 设置所有item分割线
     */
    fun setLineMarginLeftArray(values: List<Int>) = this.run {

        if (values.isEmpty()) {
            throw RuntimeException("please set divider left margin")
        }

        for (i in values.indices) {
            lineMarginLeftArray.put(i, values[i])
        }

        this
    }

    /**
     * 设置所有item分割线
     */
    fun setLineMarginLeftArray(values: Int) = this.run {

        titleTextList?.forEachIndexed { index, _ ->
            lineMarginLeftArray.put(index, values)
        }

        this
    }

    /**
     * 设置指定item分割线
     */
    fun setLineMarginLeftArray(position: Int, margin: Int) = this.run {
        lineMarginLeftArray.put(position, margin)
        this
    }

    /**
     * 是否为最后一个item
     */
    fun isLastItem() = itemPosition + 1 >= titleTextList?.size ?: 0

    /**
     * 下一个item是否设置marginTop
     */
    fun isNextTopMarginItem() = marginTopArray[itemPosition + 1] != 0

    /**
     * 设置右侧图标宽度
     */
    fun setEndIconWidth(rightIconWidthPx: Int) = this.run {
        if (rightIconWidthPx > 0) this.rightIconWidthPx = rightIconWidthPx
        this
    }

    /**
     * 设置右侧图标高度
     */
    fun setEndIconHeight(rightIconHeightPx: Int) = this.run {
        if (rightIconHeightPx > 0) this.rightIconHeightPx = rightIconHeightPx
        this
    }

    /**
     * 设置右侧图标资源
     */
    fun setEndResourceId(resourceId: Int, vararg positions: Int) = this.run {
        positions.filter { it < titleTextList?.size ?: 0 }.forEach {
            rightArrowResIds.put(it, resourceId)
        }
        this
    }

    /**
     * 设置右侧文本字体风格{@link TextStyle}
     */
    fun setEndTextStyle(endTextStyle: TextStyle) = this.run {
        this.rightTextStyle = endTextStyle
        this
    }

    /**
     * 设置右侧文本到右侧图标的margin
     */
    fun setEndTextMarginToIcon(rightTextMarginToIconPx: Int) = this.run {
        if (rightTextMarginToIconPx > 0) this.rightTextMarginToIcon = rightTextMarginToIconPx
        this
    }

    /**
     * 设置指定位置item右侧文本
     */
    fun setEndTextValue(index: Int, textValue: String) = this.run {
        rightTextList.put(index, textValue)
        this
    }

    /**
     * 设置所有位置item右侧文本
     */
    fun setEndTextValue(textValue: String) = this.run {

        if (titleTextList?.size ?: 0 <= 0) {
            throw RuntimeException("values is null")
        }

        titleTextList?.forEachIndexed { index, _ ->
            rightTextList.put(index, textValue)
        }
        this
    }

    /**
     * 设置中间文本字体风格{@link TextStyle}
     */
    fun setCenterTextStyle(centerTextStyle: TextStyle) = this.run {
        this.centerTextStyle = centerTextStyle
        this
    }

    /**
     * 设置中间文本到左侧标题的margin
     */
    fun setCenterTextMarginToTitle(centerTextMarginToTitlePx: Int) = this.run {
        if (centerTextMarginToTitlePx > 0) this.centerTextMarginToTitle = centerTextMarginToTitlePx
        this
    }

    /**
     * 设置中间文本到右侧箭头图标的margin
     */
    fun setCenterTextMarginToArrow(centerTextMarginToArrowPx: Int) = this.run {
        if (centerTextMarginToArrowPx > 0) this.centerTextMarginToArrow = centerTextMarginToArrowPx
        this
    }

    /**
     * 设置指定位置item中间文本
     */
    fun setCenterTextValue(index: Int, textValue: String) = this.run {
        centerTextList.put(index, textValue)
        this
    }

    /**
     * 设置所有位置item中间文本
     */
    fun setCenterTextValue(textValue: String) = this.run {

        if (titleTextList?.size ?: 0 <= 0) {
            throw RuntimeException("values is null")
        }

        titleTextList?.forEachIndexed { index, _ ->
            centerTextList.put(index, textValue)
        }
        this
    }

    /**
     * 添加其他自定义View
     */
    fun addOtherItemView(otherView: View) = this.run {
        this.otherItemView = otherView
        this
    }
}