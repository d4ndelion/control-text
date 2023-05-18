package com.dandelion.controltext.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toComposePaint
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.ResolvedTextDirection.Ltr
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun Modifier.shadow(
    color: Color = Color.Black,
    opacity: Float = .7f,
    radius: Dp = 10.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    width: Dp? = null,
    height: Dp? = null
) = drawBehind {
    val shadowX = ((size.width - (width?.toPx() ?: size.width)) / 2) + offsetX.toPx()
    val shadowY = ((size.height - (height?.toPx() ?: size.height)) / 2) + offsetY.toPx()
    val rect = Rect(
        Offset(shadowX, shadowY),
        Size(width?.toPx() ?: size.width, height?.toPx() ?: size.height)
    )
    val frameworkPaint = Paint().asFrameworkPaint().apply {
        this.color = Color.Transparent.toArgb()
        this.setShadowLayer(radius.toPx(), 0f, 0f, color.copy(opacity).toArgb())
    }
    drawIntoCanvas {
        it.drawRoundRect(
            rect.left,
            rect.top,
            rect.right,
            rect.bottom,
            0f,
            0f,
            frameworkPaint.toComposePaint()
        )
    }
}

@OptIn(ExperimentalContracts::class)
internal inline fun <R> fastMapRange(
    start: Int,
    end: Int,
    transform: (Int) -> R
): List<R> {
    contract { callsInPlace(transform) }
    val destination = ArrayList<R>(/* initialCapacity = */ end - start + 1)
    for (i in start..end) {
        destination.add(transform(i))
    }
    return destination
}

fun TextLayoutResult.getBoundingBoxes(
    startOffset: Int,
    endOffset: Int,
    flattenForFullParagraphs: Boolean = false
): List<Rect> {
    if (startOffset == endOffset) {
        return emptyList()
    }

    val startLineNum = getLineForOffset(startOffset)
    val endLineNum = getLineForOffset(endOffset)

    if (flattenForFullParagraphs) {
        val isFullParagraph = (startLineNum != endLineNum)
                && getLineStart(startLineNum) == startOffset
                && multiParagraph.getLineEnd(endLineNum, visibleEnd = true) == endOffset

        if (isFullParagraph) {
            return listOf(
                Rect(
                    top = getLineTop(startLineNum),
                    bottom = getLineBottom(endLineNum),
                    left = 0f,
                    right = size.width.toFloat()
                )
            )
        }
    }

    val isLtr = multiParagraph.getParagraphDirection(offset = layoutInput.text.lastIndex) == Ltr

    return fastMapRange(startLineNum, endLineNum) { lineNum ->
        Rect(
            top = getLineTop(lineNum),
            bottom = getLineBottom(lineNum),
            left = if (lineNum == startLineNum) {
                getHorizontalPosition(startOffset, usePrimaryDirection = isLtr)
            } else {
                getLineLeft(lineNum)
            },
            right = if (lineNum == endLineNum) {
                getHorizontalPosition(endOffset, usePrimaryDirection = isLtr)
            } else {
                getLineRight(lineNum)
            }
        )
    }
}

fun String.isNegativeNumber() = matches(Regex("^[-+]?\\d*\$"))

fun Color.toReadableString() = "RGB(${red.times(255).toInt()} ${green.times(255).toInt()} ${blue.times(255).toInt()})"
