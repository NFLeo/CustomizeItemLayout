package com.leo.itemlayout.item

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import com.leo.itemlayout.R

/**
 * desc：右侧带文本Item</br>
 * time: 2019/11/19-15:01</br>
 * author：Leo </br>
 * since V 1.0.0 </br>
 */
class EndTextItemView(kContext: Context) : AbsItemView(kContext) {

    private var tvEndTextView: TextView? = null

    override fun createView() {
        kConstraintSet.apply {
            tvEndTextView = TextView(context).apply {
                id = R.id.id_right_text
                centerVertically(id, ConstraintSet.PARENT_ID)
                constrainWidth(id, 0)
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                if (getEndImageView() == null) {
                    connect(id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
                } else {
                    connect(id, ConstraintSet.END, getEndImageView()?.id ?: -1, ConstraintSet.START)
                }
                connect(id, ConstraintSet.START, getTitleText().id, ConstraintSet.END)
                addView(this)
            }
        }
    }

    override fun setViewAttribute() {
        attributeCreator?.apply {
            kConstraintSet.setMargin(id, ConstraintSet.END, rightTextMarginToIcon)
            tvEndTextView?.apply {
                text = rightTextList.get(itemPosition)
                gravity = Gravity.END
                setTextStyle(rightTextStyle)
            }
        }
    }

    fun getEndTextView() = tvEndTextView
}