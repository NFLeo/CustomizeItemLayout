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

class WMSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wms)

        val otherView = View(this)
        otherView.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300)
        otherView.setBackgroundColor(Color.GREEN)

        val creator = AttributeCreator()
            .setTitleTextList(
                mutableListOf(
                    "单号扫描", "商品扫描", "实际数量", "目标货位",
                    "", "已上架数量：", "应上架数量：", "待上架数量："
                )
            )
            .setTitleTextStyle(TextStyle(15f, Color.BLACK))
            .setItemViewHeight(100)
            .setItemMode(0, ItemMode.TITLE_CENTER_EDIT)
            .setItemMode(1, ItemMode.TITLE_CENTER_EDIT)
            .setItemMode(2, ItemMode.TITLE_CENTER_EDIT)
            .setItemMode(3, ItemMode.TITLE_CENTER_EDIT)
            .setItemMode(5, ItemMode.TITLE)
            .setItemMode(6, ItemMode.TITLE)
            .setItemMode(7, ItemMode.TITLE)
            .setItemMarginTop(20)
            .setCenterTextMarginToTitle(20)
            .setCenterTextStyle(
                LabelTextStyle(
                    15F, Color.BLACK, false, R.drawable.bg_frame_white,
                    10, 30, 20, 20
                )
            )
            .setEndMargin(50)
            .setStartMargin(50)
            .addOtherItemView(otherView)
            .setItemMode(4, ItemMode.OTHER)

        ll_item.setAttributeCreator(creator)
            .setOnItemClickListener(object : BaseItemLayout.OnItemClickListener {
                override fun onItemClick(index: Int) {
                    Toast.makeText(this@WMSActivity, "点击第${index}个item", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}
