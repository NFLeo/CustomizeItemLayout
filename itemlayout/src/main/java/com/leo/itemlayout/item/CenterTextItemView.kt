package com.leo.itemlayout.item

import android.content.Context
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import com.leo.itemlayout.R

/**
 * desc：标题 + 中间显示文本</br>
 * time: 2019/11/20-9:32</br>
 * author：Leo </br>
 * since V 1.0.0 </br>
 */
open class CenterTextItemView(var kContext: Context) : AbsItemView(kContext) {

    private var tvCenterTextView: TextView? = null

    override fun createView() {
        kConstraintSet.apply {
            tvCenterTextView = TextView(kContext).apply {

                id = R.id.id_center_text
                centerVertically(id, ConstraintSet.PARENT_ID)
                connect(id, ConstraintSet.START, getTitleText().id, ConstraintSet.END)
                if (getEndImageView() == null) {
                    connect(id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
                } else {
                    connect(id, ConstraintSet.END, getEndImageView()?.id ?: -1, ConstraintSet.START)
                }
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                constrainWidth(id, 0)
                addView(this)
            }
        }
    }

    override fun setViewAttribute() {
        attributeCreator?.apply {
            kConstraintSet.apply {
                tvCenterTextView?.apply {
                    setMargin(id, ConstraintSet.START, centerTextMarginToTitle)
                    setMargin(
                        id, ConstraintSet.END,
                        if (getEndImageView() == null) endMargin else centerTextMarginToArrow
                    )
                    text = centerTextList.get(itemPosition)
                    setTextStyle(centerTextStyle)
                }
            }
        }
    }

    fun getCenterTextView() = tvCenterTextView
}