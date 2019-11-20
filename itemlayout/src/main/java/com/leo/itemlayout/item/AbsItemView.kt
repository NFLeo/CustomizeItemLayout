package com.leo.itemlayout.item

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.leo.itemlayout.AttributeCreator
import com.leo.itemlayout.R
import com.leo.itemlayout.config.*

/**
 * desc：基础item 包含左侧图标与标题</br>
 * time: 2019/11/19-15:01</br>
 * author：Leo </br>
 * since V 1.0.0 </br>
 */
abstract class AbsItemView(private var kContext: Context?) : ConstraintLayout(kContext) {

    /**
     * 标题一定存在，但右侧箭头不一定存在
     */
    private lateinit var tvLeftTitle: TextView
    private var ivRightIcon: ImageView? = null

    protected lateinit var kConstraintSet: ConstraintSet
    protected var attributeCreator: AttributeCreator? = null

    init {
        createTitleText()
    }

    private fun createTitleText() {
        kConstraintSet = ConstraintSet().apply {
            tvLeftTitle = TextView(kContext).apply {
                id = R.id.id_left_title
                centerVertically(id, ConstraintSet.PARENT_ID)
                connect(id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                constrainWidth(id, ConstraintSet.WRAP_CONTENT)
                addView(this)
            }
        }
    }

    abstract fun createView()
    abstract fun setViewAttribute()

    fun create(attrCreator: AttributeCreator) {
        attributeCreator = attrCreator
        setParentLayoutParams()
        setTitleAttribute()
        createEndArrowView()
        createView()
        setViewAttribute()
        setItemBackground()
        setItemDivider()
        kConstraintSet.applyTo(this)
    }

    private fun setParentLayoutParams() {
        if (attributeCreator?.itemViewHeightPx ?: 0 <= 0)
            throw IllegalArgumentException("item height is null")

        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.height =
            if (attributeCreator?.itemViewHeightPx == 0) ViewGroup.LayoutParams.WRAP_CONTENT
            else attributeCreator?.itemViewHeightPx ?: ITEM_HEIGHT
        setLayoutParams(layoutParams)
    }

    private fun setTitleAttribute() {
        attributeCreator?.apply {
            kConstraintSet.setMargin(tvLeftTitle.id, ConstraintSet.START, startMargin)

            tvLeftTitle.setTextStyle(titleTextStyle)

            titleTextList?.apply {
                tvLeftTitle.text = this[itemPosition]
            }
        }
    }

    /**
     * 右边箭头非必须，配置图片资源才会创建
     */
    private fun createEndArrowView() {
        if (getArrowResByItemPos() == null) return

        ivRightIcon = ImageView(context).apply {
            id = R.id.id_right_icon

            kConstraintSet.apply {
                centerVertically(id, ConstraintSet.PARENT_ID)
                connect(id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
            }

            addView(this)
        }
        setEndArrowAttribute()
    }

    private fun setEndArrowAttribute() {
        attributeCreator?.apply {
            if (getArrowResByItemPos() == null) return

            ivRightIcon?.apply {

                if (rightIconHeightPx <= 0) throw IllegalArgumentException("right icon height is 0")
                if (rightIconWidthPx <= 0) throw IllegalArgumentException("right icon width is 0")

                kConstraintSet.apply {
                    setMargin(id, ConstraintSet.END, endMargin)
                    constrainWidth(id, rightIconWidthPx)
                    constrainHeight(id, rightIconHeightPx)
                }

                getArrowResByItemPos()?.apply {
                    setImageResource(this)
                }
                scaleType = ImageView.ScaleType.FIT_XY
            }
        }
    }

    private fun setItemDivider() {
        // 最后一项 || 下一项有MarginTop 不设置分割线
        if (attributeCreator?.isHideDivider == true || attributeCreator?.isLastItem() == true
            || attributeCreator?.isNextTopMarginItem() == true
        ) return

        attributeCreator?.apply {
            View(context).apply {
                id = R.id.id_divider
                setBackgroundColor(lineColor)

                kConstraintSet.constrainHeight(id, lineHeight)
                kConstraintSet.connect(
                    id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM
                )
                kConstraintSet.connect(
                    id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START
                )
                kConstraintSet.connect(
                    id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END
                )
                val marginLeft = lineMarginLeftArray[itemPosition]
                if (marginLeft > 0) kConstraintSet.setMargin(id, ConstraintSet.START, marginLeft)
                addView(this)
            }
        }
    }

    private fun setItemBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && attributeCreator?.itemNeedBorderless == true
        ) {
            val typeArray = kContext?.obtainStyledAttributes(
                intArrayOf(R.attr.selectableItemBackgroundBorderless)
            )
            val drawable = typeArray?.getDrawable(0)
            foreground = drawable
            typeArray?.recycle()
        }
        attributeCreator?.apply {
            setBackgroundResource(itemBackgroundColor)
        }
    }

    private fun getArrowResByItemPos() = attributeCreator?.let {
        if (it.rightArrowResIds[it.itemPosition] > 0) it.rightArrowResIds[it.itemPosition] else null
    }

    fun getTitleText() = tvLeftTitle

    fun getEndImageView(): ImageView? = ivRightIcon
}

fun TextView.singleLineShow() {
    maxLines = 1
    isSingleLine = true
}

fun TextView.setTextStyle(textStyle: TextStyle?) {
    setTextColor(textStyle?.textColor ?: TEXT_COLOR)
    setTextSize(TypedValue.COMPLEX_UNIT_SP, textStyle?.textSizeSp ?: TEXT_SIZE)
    textStyle?.apply {
        if (isTextBold) {
            paint.isFakeBoldText = true
        }

        if (backgroundRes != -1) {
            setBackgroundResource(backgroundRes)
        }

        ellipsize = TextUtils.TruncateAt.END
        if (textStyle is LabelTextStyle) {
            setPaddingRelative(
                textStyle.paddingStart, textStyle.paddingTop,
                textStyle.paddingEnd, textStyle.paddingBottom
            )
        } else if (textStyle is EditTextStyle) {
            hint = textStyle.hintText
        }
    }
    singleLineShow()
}