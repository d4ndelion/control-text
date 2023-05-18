package com.dandelion.controltext.data

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelion.controltext.data.items.Item
import com.dandelion.controltext.data.items.left
import com.dandelion.controltext.data.items.steagalFontRegular
import com.dandelion.controltext.data.items.text
import com.dandelion.controltext.data.items.topStart

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
    override var paddingRight: Dp? = null,
    override var paddingBottom: Dp? = null,
    override var borderWidth: Dp = 1.dp,
    override var borderColor: Color = Black,
    override var isBorderColorClear: Boolean = false,
    override var background: Color = White,
    override var isBackgroundClear: Boolean = true,
    override var shadowColor: Color = Black,
    override var isShadowColorClear: Boolean = true,
    override var shadowOpacity: Dp = 10.dp,
    override var shadowOffsetX: Dp = 0.dp,
    override var shadowOffsetY: Dp = 0.dp,
    override var shadowSize: Dp = 0.dp,
    override var font: Item<FontFamily> = steagalFontRegular,
    override var fontSize: TextUnit = 10.sp,
    override var textColor: Color = Black,
    override var isTextColorClear: Boolean = false,
    override var lineSpacing: TextUnit = TextUnit.Unspecified,
    override var lineCount: Int = 0,
    override var isScrollable: Boolean = true,
    override var textAlignment: Item<TextAlign> = left,
    override var underlineThickness: Dp = 0.dp,
    override var underlineColor: Color = Black,
    override var isUnderlineColorClear: Boolean = false,
    var content: String = "",
    var link: String = "",
    var linkColor: Color = Blue,
    var isLinkColorClear: Boolean = false,
    var linkFont: Item<FontFamily> = steagalFontRegular,
    var linkFontSize: TextUnit = 10.sp,
    var linkUnderlineColor: Color = Blue,
    var isLinkUnderlineColorClear: Boolean = false,
    var linkUnderlineThickness: Dp = 1.dp
) : CommonOptions {
    override fun toString() = """  
    Overall features
            Input is disabled.
            radius = $radius

    Position
            xOffset = $xOffset
            yOffset = $yOffset
            relativePosition = ${relativePosition.name}

    Size
            width = $width
            minWidth = $minWidth
            height = $height
            minHeight = $minHeight

    Padding
            paddingTop = $paddingTop
            paddingRight = $paddingRight
            paddingBottom = $paddingBottom
            paddingLeft = $paddingLeft

    Border
            borderWidth= $borderWidth
            borderColor = RGB(${borderColor.red.times(255).toInt()} ${borderColor.green.times(255).toInt()} ${borderColor.blue.times(255).toInt()})

    Background
            background = RGB(${background.red.times(255).toInt()} ${background.green.times(255).toInt()} ${background.blue.times(255).toInt()})
            background clear = $isBackgroundClear
            shadowColor = RGB(${shadowColor.red.times(255).toInt()} ${shadowColor.green.times(255).toInt()} ${shadowColor.blue.times(255).toInt()})
            shadowColor clear = $isShadowColorClear
            shadowOpacity = $shadowOpacity
            shadowOffsetX = $shadowOffsetX
            shadowOffsetY = $shadowOffsetY
            shadowSize = $shadowSize

    Text
            font = ${font.name}
            fontSize = $fontSize
            textColor = RGB(${textColor.red.times(255).toInt()} ${textColor.green.times(255).toInt()} ${textColor.blue.times(255).toInt()})
            textColor clear = $isTextColorClear
            lineSpacing = $lineSpacing
            lineCount = $lineCount
            isScrollable = $isScrollable
            textAlignment = ${textAlignment.name}

    Underline
            underlineThickness = $underlineThickness
            underlineColor = RGB(${underlineColor.red.times(255).toInt()} ${underlineColor.green.times(255).toInt()} ${underlineColor.blue.times(255).toInt()})
            underlineColor clear = $isUnderlineColorClear

    Features
            content = $content
            link = $link
            linkColor = RGB(${linkColor.red.times(255).toInt()} ${linkColor.green.times(255).toInt()} ${linkColor.blue.times(255).toInt()})
            linkColor clear = $isLinkColorClear
            linkFont = ${linkFont.name}
            linkFontSize = $linkFontSize
            linkUnderlineColor = RGB(${linkUnderlineColor.red.times(255).toInt()} ${linkUnderlineColor.green.times(255).toInt()} ${linkUnderlineColor.blue.times(255).toInt()}) 
            linkUnderLineColor clear = $isLinkUnderlineColorClear
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
    isBorderColorClear,
    background,
    isBackgroundClear,
    shadowColor,
    isShadowColorClear,
    shadowOpacity,
    shadowOffsetX,
    shadowOffsetY,
    shadowSize,
    font,
    fontSize,
    textColor,
    isTextColorClear,
    lineSpacing,
    lineCount,
    isScrollable,
    textAlignment,
    underlineThickness,
    underlineColor,
    isUnderlineColorClear,
    dynamicHeight = false,
    defaultText = "",
    defaultTextColor = Black,
    isDefaultTextColorClear = false,
    maxCharacters = 0,
    secureTextEntry = false,
    keyboardType = text,
    executionDelay = 0f,
    identifier = "",
    nextResponder = "",
    firstResponder = false
)
