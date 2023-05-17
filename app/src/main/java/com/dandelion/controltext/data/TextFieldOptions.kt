package com.dandelion.controltext.data

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TextFieldOptions(
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
    override var background: Color = White,
    override var shadowColor: Color = Black,
    override var shadowOpacity: Dp = 4.dp,
    override var shadowOffsetX: Dp = 0.dp,
    override var shadowOffsetY: Dp = 0.dp,
    override var shadowSize: Dp = 0.dp,
    override var font: Item<FontFamily> = steagalFontRegular,
    override var fontSize: TextUnit = 10.sp,
    override var textColor: Color = Black,
    override var lineSpacing: TextUnit = TextUnit.Unspecified,
    override var lineCount: Int = 0,
    override var isScrollable: Boolean = true,
    override var textAlignment: Item<TextAlign> = left,
    override var underlineThickness: Dp = 0.dp,
    override var underlineColor: Color = Black,
    var dynamicHeight: Boolean = false, // todo
    var defaultText: String = "",
    var defaultTextColor: Color = Black,
    var maxCharacters: Int = 0,
    var secureTextEntry: Boolean = false,
    var keyboardType: Item<KeyboardType> = text,
    var executionDelay: Float = 0f,
    var identifier: String = "",
    var nextResponder: String = "",
    var firstResponder: Boolean = false
) : CommonOptions {
    override fun toString() = """  
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
            shadowColor = RGB(${shadowColor.red.times(255).toInt()} ${shadowColor.green.times(255).toInt()} ${shadowColor.blue.times(255).toInt()})
            shadowOpacity = $shadowOpacity
            shadowOffsetX = $shadowOffsetX
            shadowOffsetY = $shadowOffsetY
            shadowSize = $shadowSize

    Text
            font = ${font.name}
            fontSize = $fontSize
            textColor = RGB(${textColor.red.times(255).toInt()} ${textColor.green.times(255).toInt()} ${textColor.blue.times(255).toInt()})
            lineSpacing = $lineSpacing
            lineCount = $lineCount
            isScrollable = $isScrollable
            textAlignment = ${textAlignment.name}

    Underline
            underlineThickness = $underlineThickness
            underlineColor = RGB(${underlineColor.red.times(255).toInt()} ${underlineColor.green.times(255).toInt()} ${underlineColor.blue.times(255).toInt()})

    Features
            defaultText = $defaultText
            defaultTextColor = RGB(${defaultTextColor.red.times(255).toInt()} ${defaultTextColor.green.times(255).toInt()} ${defaultTextColor.blue.times(255).toInt()})
            maxCharacters = $maxCharacters
            secureTextEntry = $secureTextEntry
            keyboardType = ${keyboardType.name}
            executionDelay = $executionDelay
            identifier = $identifier
            nextResponder = $nextResponder
            firstResponder = $firstResponder
    """
}

fun TextFieldOptions.toTextOptions() = TextOptions(
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
    content = "",
    link = "",
    linkColor = Black,
    linkFont = steagalFontRegular,
    linkFontSize = 10.sp,
    linkUnderlineColor = Transparent,
    linkUnderlineThickness = 0.dp
)