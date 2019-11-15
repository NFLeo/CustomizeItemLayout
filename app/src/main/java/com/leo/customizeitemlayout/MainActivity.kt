package com.leo.customizeitemlayout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.leo.itemlayout.AttributeCreator
import com.leo.itemlayout.BaseItemLayout
import com.leo.itemlayout.config.ItemMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val creator = AttributeCreator().setTitleTextList(mutableListOf("姓名", "性别", "年龄"))
            .setTitleTextColor(Color.RED)
            .setItemViewHeight(100)
            .setItemMode(ItemMode.NORMAL)
            .setItemBackgroundColor(R.color.colorPrimary)
            .setItemMarginTop(1, 30)
            .setIconStartMargin(50)
            .setItemNeedBorderless(true)
            .setLineHeight(2)
            .setLineColor(Color.RED)
            .setTitleMarginIcon(50)
            .setLineMarginLeftArray(1, 20)
            .setIconResouceIds(
                mutableListOf(
                    com.leo.itemlayout.R.drawable.icon_next_grey,
                    com.leo.itemlayout.R.drawable.icon_next_grey,
                    com.leo.itemlayout.R.drawable.icon_next_grey
                )
            )

        ll_item.setAttributeCreator(creator)
            .setOnItemClickListener(object : BaseItemLayout.OnItemClickListener {
                override fun onItemClick(index: Int) {
                    Toast.makeText(this@MainActivity, "点击第${index}个item", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
