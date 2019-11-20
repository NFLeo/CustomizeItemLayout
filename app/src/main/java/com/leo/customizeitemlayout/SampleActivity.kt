package com.leo.customizeitemlayout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.leo.itemlayout.AttributeCreator
import com.leo.itemlayout.BaseItemLayout
import com.leo.itemlayout.config.ItemMode
import com.leo.itemlayout.config.LabelTextStyle
import com.leo.itemlayout.config.TextStyle
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val otherView = View(this)
        otherView.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300)
        otherView.setBackgroundColor(Color.GREEN)

        val creator = AttributeCreator()
            .setTitleTextList(mutableListOf("标题：", "图标标题：", "右侧文本：", "中间文本：", "中间输入框：","其他："))
            .setTitleTextStyle(TextStyle(18f, Color.RED))
            .setItemViewHeight(120)
            .setItemMode(0, ItemMode.TITLE)
            .setItemMode(1, ItemMode.ICON_TITLE)
            .setItemMode(2, ItemMode.TITLE_END_TEXT)
            .setItemMode(3, ItemMode.TITLE_CENTER_TEXT)
            .setItemMode(4, ItemMode.TITLE_CENTER_EDIT)
            .setCenterTextValue(3, "中间文本内容中间文本内容中间文本内容中间文本内容中间文本内容中间文本内容")
            .setCenterTextStyle(
                LabelTextStyle(
                    15F, Color.BLACK, false, R.drawable.bg_frame_white,
                    10, 30, 20, 20
                )
            )
            .setEndTextValue(2, "18 years old")
            .setEndResourceId(R.drawable.icon_next_grey, 0, 1, 2)
            .setEndTextStyle(TextStyle(15f, R.color.white, true))
            .setEndMargin(50)
            .setStartMargin(50)
            .setEndTextMarginToIcon(10)
            .setItemBackgroundColor(R.color.colorPrimary)
            .setItemMarginTop(1, 30)
            .setItemNeedBorderless(true)
            .setLineHeight(2)
            .setLineColor(Color.RED)
            .setTitleMarginIcon(20)
            .addOtherItemView(otherView)
            .setItemMode(5, ItemMode.OTHER)
            .setLineMarginLeftArray(1, 20)
            .setIconResourceIds(mutableListOf(R.drawable.icon_next_grey, R.drawable.icon_next_grey))

        ll_item.setAttributeCreator(creator)
            .setOnItemClickListener(object : BaseItemLayout.OnItemClickListener {
                override fun onItemClick(index: Int) {
                    Toast.makeText(this@SampleActivity, "点击第${index}个item", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}
