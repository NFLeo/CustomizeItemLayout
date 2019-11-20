package com.leo.itemlayout.config

enum class ItemMode {
    TITLE,                      // 左侧为文字，右侧无箭头
    ICON_TITLE,                 // 包含左侧图标和标题
    TITLE_END_TEXT,             // 左侧为文字，右侧有箭头
    TITLE_CENTER_TEXT,          // 标题 + 文本框
    TITLE_CENTER_EDIT,          // 标题 + 输入框
    OTHER                       // 其他自定义View
}