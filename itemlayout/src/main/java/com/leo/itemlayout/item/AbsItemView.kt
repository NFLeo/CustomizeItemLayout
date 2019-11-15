package com.leo.itemlayout.item

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.leo.itemlayout.AttributeCreator
import com.leo.itemlayout.R
import com.leo.itemlayout.config.ITEM_HEIGHT

abstract class AbsItemView(private var kContext: Context?) : ConstraintLayout(kContext) {

    private lateinit var ivRightIcon: ImageView
    private lateinit var tvLeftTitle: TextView

    private lateinit var kConstraintSet: ConstraintSet
    private var attributeCreator: AttributeCreator? = null

    init {
        initView()
        initConstrainSet()
    }

    private fun initView() {
        ivRightIcon = ImageView(kContext).apply { id = R.id.id_left_icon }
        tvLeftTitle = TextView(kContext).apply { id = R.id.id_left_title }
        addView(ivRightIcon)
        addView(tvLeftTitle)
    }

    private fun initConstrainSet() {
        kConstraintSet = ConstraintSet().apply {
            ivRightIcon.apply {
                connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
                connect(id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
                connect(id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                constrainWidth(id, ConstraintSet.WRAP_CONTENT)
            }

            tvLeftTitle.apply {
                connect(id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
                connect(id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
                connect(id, ConstraintSet.START, ivRightIcon.id, ConstraintSet.END)
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                constrainWidth(id, ConstraintSet.WRAP_CONTENT)
            }
        }
    }

    private fun addWidget() {
        kConstraintSet.applyTo(this)
    }

    fun create(attrCreator: AttributeCreator) {
        attributeCreator = attrCreator
        setWidthAndHeightSet()
        setIconSet()
        setTitleTextSet()
        setItemBackground()
        setItemDivider()
        addWidget()
    }

    private fun setIconSet() {
        attributeCreator?.apply {
            if (leftResourceIds == null || leftResourceIds?.size == 0) return

            if (leftIconHeightPx <= 0) throw IllegalArgumentException("icon height is 0")
            if (leftIconWidthPx <= 0) throw IllegalArgumentException("icon width is 0")

            kConstraintSet.setMargin(ivRightIcon.id, ConstraintSet.START, leftIconStartMargin)
            kConstraintSet.constrainWidth(ivRightIcon.id, leftIconWidthPx)
            kConstraintSet.constrainHeight(ivRightIcon.id, leftIconHeightPx)

            ivRightIcon.setImageResource(leftResourceIds!![itemPosition])
            ivRightIcon.scaleType = ImageView.ScaleType.FIT_XY
        }
    }

    private fun setTitleTextSet() {
        attributeCreator?.apply {
            kConstraintSet.setMargin(tvLeftTitle.id, ConstraintSet.START, titleMarginIcon)
            tvLeftTitle.setTextColor(titleTextColor)
            tvLeftTitle.textSize = titleTextSize

            titleTextList?.apply {
                tvLeftTitle.text = this[itemPosition]
            }
        }
    }

    private fun setWidthAndHeightSet() {
        if (attributeCreator?.itemViewHeightPx ?: 0 <= 0)
            throw IllegalArgumentException("item height is null")

        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        layoutParams.height = attributeCreator?.itemViewHeightPx ?: ITEM_HEIGHT
        setLayoutParams(layoutParams)
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
}