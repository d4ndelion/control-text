package com.dandelion.controltext.data

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
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
import com.dandelion.controltext.util.toReadableString

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
    override var borderWidth: Dp = 0.dp,
    override var borderColor: Color = Black,
    override var isBorderColorClear: Boolean = false,
    override var background: Color = White,
    override var isBackgroundClear: Boolean = true,
    override var shadowColor: Color = Black,
    override var isShadowColorClear: Boolean = true,
    override var shadowOpacity: Float = .7f,
    override var shadowRadius: Dp = 10.dp,
    override var shadowOffsetX: Dp = 0.dp,
    override var shadowOffsetY: Dp = 0.dp,
    override var shadowHeight: Dp? = null,
    override var shadowWidth: Dp? = null,
    override var font: Item<FontFamily> = steagalFontRegular,
    override var fontSize: TextUnit = 10.sp,
    override var textColor: Color = Black,
    override var isTextColorClear: Boolean = false,
    override var lineSpacing: TextUnit = 0.sp,
    override var lineCount: Int = 0,
    override var isScrollable: Boolean = true,
    override var textAlignment: Item<TextAlign> = left,
    override var underlineThickness: Dp = 0.dp,
    override var underlineColor: Color = Black,
    override var isUnderlineColorClear: Boolean = false,
    var dynamicHeight: Boolean = false,
    var defaultText: String = "",
    var defaultTextColor: Color = Black,
    var isDefaultTextColorClear: Boolean = false,
    var maxCharacters: Int = 0,
    var secureTextEntry: Boolean = false,
    var keyboardType: Item<KeyboardType> = text,
    var executionDelay: Float = 0f,
    var identifier: String = "",
    var nextResponder: String = "",
    var firstResponder: Boolean = false
) : CommonOptions {
    override fun toString() = """  
            Input is enabled.
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
            borderColor = ${borderColor.toReadableString()}

    Background
            background = ${background.toReadableString()}
            background clear = $isBackgroundClear
            shadowColor = ${shadowColor.toReadableString()}
            shadowColor clear = $isShadowColorClear
            shadowOpacity = $shadowOpacity
            shadowRadius = $shadowRadius
            shadowOffsetX = $shadowOffsetX
            shadowOffsetY = $shadowOffsetY
            shadowWidth = $shadowWidth
            shadowHeight = $shadowHeight

    Text
            font = ${font.name}
            fontSize = $fontSize
            textColor = ${textColor.toReadableString()}
            textColor clear = $isTextColorClear
            lineSpacing = $lineSpacing
            lineCount = $lineCount
            isScrollable = $isScrollable
            textAlignment = ${textAlignment.name}

    Underline
            underlineThickness = $underlineThickness
            underlineColor = ${underlineColor.toReadableString()}
            underlineColor clear = $isUnderlineColorClear

    Features
            defaultText = $defaultText
            defaultTextColor = ${defaultTextColor.toReadableString()}
            defaultTextColor clear = $isDefaultTextColorClear
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
    isBorderColorClear,
    background,
    isBackgroundClear,
    shadowColor,
    isShadowColorClear,
    shadowOpacity,
    shadowRadius,
    shadowOffsetX,
    shadowOffsetY,
    shadowHeight,
    shadowWidth,
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
    content = "",
    link = "",
    linkColor = Blue,
    isLinkColorClear = false,
    linkFont = steagalFontRegular,
    linkFontSize = 10.sp,
    linkUnderlineColor = Blue,
    isLinkUnderlineColorClear = false,
    linkUnderlineThickness = 1.dp
)
