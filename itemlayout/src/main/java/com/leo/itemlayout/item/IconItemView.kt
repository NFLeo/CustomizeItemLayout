package com.leo.itemlayout.item

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet
import com.leo.itemlayout.R

class IconItemView(var kContext: Context) : AbsItemView(kContext) {

    private var ivLeftIcon: ImageView? = null

    override fun createView() {
        kConstraintSet.apply {
            ivLeftIcon = ImageView(kContext).apply {
                id = R.id.id_left_icon
                centerVertically(id, ConstraintSet.PARENT_ID)
                connect(id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
                clear(getTitleText().id, ConstraintSet.START)
                connect(getTitleText().id, ConstraintSet.START, id, ConstraintSet.END)
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                constrainWidth(id, ConstraintSet.WRAP_CONTENT)
                addView(this)
            }
        }
    }

    override fun setViewAttribute() {
        attributeCreator?.apply {
            if (leftResourceIds == null || leftResourceIds?.size == 0) return

            if (leftIconHeightPx <= 0) throw IllegalArgumentException("icon height is 0")
            if (leftIconWidthPx <= 0) throw IllegalArgumentException("icon width is 0")

            kConstraintSet.apply {
                ivLeftIcon?.apply {
                    setMargin(id, ConstraintSet.START, startMargin)
                    setMargin(getTitleText().id, ConstraintSet.START, titleMarginIcon)
                    constrainWidth(id, leftIconWidthPx)
                    constrainHeight(id, leftIconHeightPx)

                    setImageResource(leftResourceIds!![itemPosition])
                    scaleType = ImageView.ScaleType.FIT_XY
                }
            }
        }
    }
}