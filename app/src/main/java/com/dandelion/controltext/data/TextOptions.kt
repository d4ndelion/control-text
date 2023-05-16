package com.dandelion.controltext.data

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TextOptions(
    override var radius: Dp = 0.dp,
    override var xOffset: Dp = 0.dp,
    override var yOffset: Dp = 0.dp,
    override var relativePosition: Item<Alignment> = topStart,
    override var width: Dp = 0.dp,
    override var minWidth: Dp = 0.dp,
    override var height: Dp = 0.dp,
    override var minHeight: Dp = 0.dp,
    override var paddingLeft: Dp? = null,
    override var paddingTop: Dp = 0.dp,
    override var paddingRight: Dp? = 0.dp,
    override var paddingBottom: Dp? = 0.dp,
    override var borderWidth: Dp = 0.dp,
    override var borderColor: Color = Color.Black,
    override var background: Color = Color.White,
    override var shadowColor: Color = Color.Black,
    override var shadowOpacity: Dp = 0.dp,
    override var shadowOffsetX: Dp = 0.dp,
    override var shadowOffsetY: Dp = 0.dp,
    override var shadowSize: Dp = 0.dp,
    override var font: Item<FontFamily> = steagalFontRegular,
    override var fontSize: TextUnit = 10.sp,
    override var textColor: Color = Color.Black,
    override var lineSpacing: TextUnit = TextUnit.Unspecified,
    override var lineCount: Int = 0,
    override var isScrollable: Boolean = true,
    override var textAlignment: Item<TextAlign> = left,
    override var underlineThickness: Dp = 0.dp,
    override var underlineColor: Color = Color.Black,
    var content: String = "",
    var link: String = "",
    var linkColor: Color = Color.Black,
    var linkFont: Item<FontFamily> = steagalFontRegular,
    var linkFontSize: TextUnit = 10.sp,
    var linkUnderlineColor: Color = Transparent,
    var linkUnderlineThickness: Dp = 0.dp
) : CommonOptions {
    override fun toString() = """  
    Overall features
            Input is disabled.
            radius = $radius

    Position
            xOffset = $xOffset
            yOffset = $yOffset
            relativePosition = $relativePosition

    Size
            width = $width
            minWidth = $minWidth
            height = $height
            minHeight = $minHeight

    Padding
            paddingLeft = $paddingLeft
            paddingTop = $paddingTop
            paddingRight = $paddingRight
            paddingBottom = $paddingBottom

    Border
            borderWidth= $borderWidth
            borderColor = $borderColor

    Background
            background = $background
            shadowColor = $shadowColor
            shadowOpacity = $shadowOpacity
            shadowOffsetX = $shadowOffsetX
            shadowOffsetY = $shadowOffsetY
            shadowSize = $shadowSize

    Text
            font = ${font.name}
            fontSize = $fontSize
            textColor = $textColor
            lineSpacing = $lineSpacing
            lineCount = $lineCount
            isScrollable = $isScrollable
            textAlignment = $textAlignment

    Underline
            underlineThickness = $underlineThickness
            underlineColor = $underlineColor

    Features
            content = $content
            link = $link
            linkColor = $linkColor
            linkFont = $linkFont
            linkFontSize = $linkFontSize
            linkUnderlineColor = $linkUnderlineColor
            linkUnderlineThickness = $linkUnderlineThickness
    """
}

fun TextOptions.toTextFieldOptions() = TextFieldOptions(
    radius,
    xOffset,
    yOffset,
    relativePosition,
    width,
    minWidth,
    height,
    minHeight,
    paddingLeft,
    paddingTop,
    paddingRight,
    paddingBottom,
    borderWidth,
    borderColor,
    background,
    shadowColor,
    shadowOpacity,
    shadowOffsetX,
    shadowOffsetY,
    shadowSize,
    font,
    fontSize,
    textColor,
    lineSpacing,
    lineCount,
    isScrollable,
    textAlignment,
    underlineThickness,
    underlineColor,
    defaultText = "",
    defaultTextColor = Transparent,
    maxCharacters = 0,
    secureTextEntry = false,
    keyboardType = text,
    executionDelay = 0L,
    identifier = "",
    nextResponder = "",
    firstResponder = false
)
