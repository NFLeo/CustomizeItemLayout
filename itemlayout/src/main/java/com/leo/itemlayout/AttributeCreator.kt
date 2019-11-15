package com.leo.itemlayout

import android.util.SparseArray
import android.util.SparseIntArray
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

    /**
     * 单个Item
     */
    var itemPosition: Int = 0                               // 当前条目所在位置
    var itemViewHeightPx: Int = ITEM_HEIGHT                 // 每个条目的高度
    var itemBackgroundColor: Int = R.color.white
    var itemNeedBorderless = true

    /**
     * 左侧图标
     */
    var leftIconWidthPx: Int = ICON_HEIGHT
    var leftIconHeightPx: Int = ICON_WIDTH
    var leftResourceIds: MutableList<Int>? = null
    var leftIconStartMargin: Int = ITEM_MARGIN

    /**
     * 标题文本
     */
    var titleMarginIcon: Int = ITEM_MARGIN                   // 标题与图标的margin
    var titleTextSize: Float = TEXT_SIZE
    var titleTextColor: Int = TEXT_COLOR
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
    var rightResourceId: Int? = null
    var rightIconEndMargin: Int = ITEM_MARGIN

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
    fun setIconResouceIds(iconResIds: MutableList<Int>) = this.run {
        leftResourceIds = iconResIds
        this
    }

    /**
     * 图标距离起始位置的距离
     */
    fun setIconStartMargin(marginStartPx: Int) = this.run {
        if (marginStartPx > 0) leftIconStartMargin = marginStartPx
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
     * 设置标题文本字体大小
     */
    fun setTitleTextSize(textSizePx: Float) = this.run {
        if (textSizePx > 0) titleTextSize = textSizePx
        this
    }

    /**
     * 设置标题文本字体颜色
     */
    fun setTitleTextColor(textColor: Int) = this.run {
        titleTextColor = textColor
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
    fun setRightIconWidth(rightIconWidthPx: Int) = this.run {
        if (rightIconWidthPx > 0) this.rightIconWidthPx = rightIconWidthPx
        this
    }

    /**
     * 设置右侧图标高度
     */
    fun setRightIconHeight(rightIconHeightPx: Int) = this.run {
        if (rightIconHeightPx > 0) this.rightIconHeightPx = rightIconHeightPx
        this
    }

    /**
     * 设置右侧图标资源
     */
    fun setRightResourceId(rightResourceId: Int) = this.run {
        this.rightResourceId = rightResourceId
        this
    }

    /**
     * 设置右侧图标具体右边边界距离
     */
    fun setRightIconEndMargin(rightIconEndMarginPx: Int) = this.run {
        this.rightIconEndMargin = rightIconEndMarginPx
        this
    }
}