package com.leo.itemlayout.item

import android.content.Context
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintSet
import com.leo.itemlayout.R

/**
 * desc：标题 + 中间显示输入框</br>
 * time: 2019/11/20-13:59</br>
 * author：Leo </br>
 * since V 1.0.0 </br>
 */
open class CenterEditItemView(private var kContext: Context) : AbsItemView(kContext) {

    private var tvCenterEditText: EditText? = null

    override fun createView() {
        kConstraintSet.apply {
            tvCenterEditText = EditText(kContext).apply {

                id = R.id.id_center_edit
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
                tvCenterEditText?.apply {
                    setMargin(id, ConstraintSet.START, centerTextMarginToTitle)
                    setMargin(
                        id, ConstraintSet.END,
                        if (getEndImageView() == null) endMargin else centerTextMarginToArrow
                    )
                    setText(centerTextList.get(itemPosition))
                    setTextStyle(centerTextStyle)
                }
            }
        }
    }

    fun getCenterEditText() = tvCenterEditText
}