package com.leo.itemlayout.config

/**
 * 文字样式实体类
 */
open class TextStyle {
    var textSizeSp: Float = TEXT_SIZE
    var textColor: Int = TEXT_COLOR
    var isTextBold: Boolean = false
    var backgroundRes: Int = -1

    constructor()

    constructor(
        textSizeSp: Float, textColor: Int,
        isTextBold: Boolean = false, backgroundRes: Int = -1
    ) {
        this.textSizeSp = textSizeSp
        this.textColor = textColor
        this.isTextBold = isTextBold
        this.backgroundRes = backgroundRes
    }
}

class LabelTextStyle : TextStyle {
    var paddingStart = 0
    var paddingEnd = 0
    var paddingTop = 0
    var paddingBottom = 0

    constructor(
        textSizeSp: Float, textColor: Int, isTextBold: Boolean = false, backgroundRes: Int = -1
    ) : super(textSizeSp, textColor, isTextBold, backgroundRes)

    constructor(
        textSizeSp: Float, textColor: Int, isTextBold: Boolean = false, backgroundRes: Int = -1,
        paddingStart: Int, paddingEnd: Int, paddingTop: Int, paddingBottom: Int
    ) : super(textSizeSp, textColor, isTextBold, backgroundRes) {
        this.paddingStart = paddingStart
        this.paddingEnd = paddingEnd
        this.paddingTop = paddingTop
        this.paddingBottom = paddingBottom
    }
}

class EditTextStyle : TextStyle {
    var hintText = ""

    constructor(
        textSizeSp: Float, textColor: Int, isTextBold: Boolean = false,
        backgroundRes: Int = -1, hintText: String
    ) : super(textSizeSp, textColor, isTextBold, backgroundRes) {
        this.hintText = hintText
    }

    constructor(
        textSizeSp: Float, textColor: Int, isTextBold: Boolean = false,
        backgroundRes: Int = -1
    ) : super(textSizeSp, textColor, isTextBold, backgroundRes)
}