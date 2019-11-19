package com.leo.itemlayout.item

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import com.leo.itemlayout.AttributeCreator
import com.leo.itemlayout.R
import com.leo.itemlayout.config.TEXT_COLOR
import com.leo.itemlayout.config.TEXT_SIZE

/**
 * desc：右侧带文本Item</br>
 * time: 2019/11/19-15:01</br>
 * author：Leo </br>
 * since V 1.0.0 </br>
 */
class EndTextItemView(kContext: Context) : ArrowItemView(kContext) {

    private var tvEndTextView: TextView? = null

    override fun createWidget() {
        super.createWidget()
        attributeCreator?.apply {
            tvEndTextView = TextView(context).apply {
                id = R.id.id_right_text
                setTextConstraintSet(this)
                addView(this)
            }

            setTextStyle()
        }
    }

    private fun AttributeCreator.setTextConstraintSet(view: TextView) {
        kConstraintSet.apply {
            view.apply {
                setMargin(id, ConstraintSet.END, rightIconEndMargin)
                centerVertically(id, ConstraintSet.PARENT_ID)
                constrainWidth(id, 0)
                constrainHeight(id, ConstraintSet.WRAP_CONTENT)
                connect(
                    id, ConstraintSet.END, getEndImageView()?.id ?: -1, ConstraintSet.START
                )
                connect(
                    id, ConstraintSet.START, getTitleText().id, ConstraintSet.END
                )
            }
        }
    }

    private fun AttributeCreator.setTextStyle() {

        tvEndTextView?.apply {
            text = rightTextList.get(itemPosition)
            singleLineShow()
            gravity = Gravity.END
            rightTextStyle.let {
                setTextColor(it?.textColor ?: TEXT_COLOR)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, it?.textSizeSp ?: TEXT_SIZE)
                if (it?.isTextBold == true) {
                    paint.isFakeBoldText = true
                }
            }
        }
    }

    fun getEndTextView() = tvEndTextView
}