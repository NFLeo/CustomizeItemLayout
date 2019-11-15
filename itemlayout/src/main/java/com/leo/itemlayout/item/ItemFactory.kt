package com.leo.itemlayout.item

import android.content.Context
import com.leo.itemlayout.AttributeCreator
import com.leo.itemlayout.config.ItemMode

class ItemFactory(var context: Context) : AbsItemFactory() {

    override fun <T : AbsItemView> createItem(
        mode: ItemMode?, attrs: AttributeCreator
    ): T {

        if (mode == null) throw RuntimeException("please set mode")

        var item: AbsItemView? = null

        try {
            when (mode) {
                ItemMode.NORMAL -> item = NormalItemView(context)
            }

            item?.create(attrs)

        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
        return item as T
    }

}