package com.leo.itemlayout.item

import com.leo.itemlayout.AttributeCreator
import com.leo.itemlayout.config.ItemMode

abstract class AbsItemFactory {

    abstract fun <T : AbsItemView> createItem(mode: ItemMode?, attrs: AttributeCreator): T
}