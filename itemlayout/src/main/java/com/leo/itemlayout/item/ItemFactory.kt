package com.leo.itemlayout.item

import android.content.Context
import com.leo.itemlayout.AttributeCreator
import com.leo.itemlayout.config.ItemMode

class ItemFactory(var context: Context) : AbsItemFactory() {

    override fun <T : AbsItemView> createItem(
        mode: ItemMode?, attrs: AttributeCreator
    ): T {

        if (mode == null) throw RuntimeException("please set mode")

        val item: AbsItemView?

        try {
            item = when (mode) {
                ItemMode.ICON_TITLE -> IconItemView(context)
                ItemMode.TITLE_END_TEXT -> EndTextItemView(context)
                ItemMode.TITLE_CENTER_TEXT -> CenterTextItemView(context)
                ItemMode.TITLE_CENTER_EDIT -> CenterEditItemView(context)
                else -> TitleItemView(context)
            }

            item.create(attrs)

        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
        return item as T
    }

}