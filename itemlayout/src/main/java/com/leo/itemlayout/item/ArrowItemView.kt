package com.leo.itemlayout.item

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet
import com.leo.itemlayout.R

/**
 * desc：右侧带图标ItemView</br>
 * time: 2019/11/15-15:27</br>
 * author：Leo </br>
 * since V 1.0.0 </br>
 */
open class ArrowItemView(kContext: Context) : AbsItemView(kContext) {

    private var ivRightIcon: ImageView? = null

    /**
     * 创建箭头
     */
    override fun createWidget() {
        attributeCreator?.apply {
            if (rightResourceId == null) return

            ivRightIcon = ImageView(context).apply {
                id = R.id.id_right_icon

                if (rightIconHeightPx <= 0) throw IllegalArgumentException("right icon height is 0")
                if (rightIconWidthPx <= 0) throw IllegalArgumentException("right icon width is 0")

                kConstraintSet.apply {
                    setMargin(id, ConstraintSet.END, rightIconEndMargin)
                    constrainWidth(id, rightIconWidthPx)
                    constrainHeight(id, rightIconHeightPx)
                    centerVertically(id, ConstraintSet.PARENT_ID)
                    connect(id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
                }

                setImageResource(rightResourceId!!)
                scaleType = ImageView.ScaleType.FIT_XY
                addView(this)
            }
        }
    }

    fun getEndImageView() = ivRightIcon
}