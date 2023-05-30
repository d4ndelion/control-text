package com.dandelion.controltext.ui.screens.output

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp.Companion.Unspecified
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelion.controltext.data.TextFieldOptions
import com.dandelion.controltext.data.TextOptions
import com.dandelion.controltext.data.cachedFields
import com.dandelion.controltext.setScreen
import com.dandelion.controltext.ui.screens.Screen.Enter
import com.dandelion.controltext.util.getBoundingBoxes
import com.dandelion.controltext.util.shadow
import java.lang.Integer.MAX_VALUE

private const val ANNOTATION_TAG_LINK_UNDERLINE = "underline_link"
private const val ANNOTATION_TAG_UNDERLINE = "underline_text"
private const val ANNOTATION_IGNORE = "ignored"

@Composable
fun OutputScreenContent() {

    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .background(Blue)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                focusManager.clearFocus()
            }
    ) {
        Column(Modifier.background(White), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(White)
            ) {
                val requesters = cachedFields.map {
                    when (it) {
                        is TextFieldOptions -> Pair(it.identifier, FocusRequester())
                        else -> Pair("", null)
                    }
                }
                val nextRequesters = cachedFields.mapIndexed { index, requester ->
                    when (requester) {
                        is TextFieldOptions -> Pair(
                            requesters.find { requester.identifier == it.first && requester.identifier.isNotEmpty() }
                                ?: requesters[index],
                            requesters.find {
                                requester.nextResponder == it.first && requester.identifier.isNotEmpty() && requesters.map { req -> req.first }
                                    .contains(requester.identifier)
                            }
                                ?: try {
                                    requesters[index + 1]
                                } catch (exception: IndexOutOfBoundsException) {
                                    null
                                }
                        )

                        else -> Pair(Pair("", FocusRequester()), Pair("", FocusRequester()))
                    }
                }
                repeat(cachedFields.size) { num ->
                    val alignmentModifier = Modifier
                        .offset(cachedFields[num].xOffset, cachedFields[num].yOffset)
                        .align(cachedFields[num].relativePosition.item)
                    when (cachedFields[num]) {
                        is TextOptions -> ResultText(
                            modifier = alignmentModifier,
                            options = cachedFields[num] as TextOptions,
                            focusRequester = requesters[num].second
                        )

                        is TextFieldOptions -> ResultTextField(
                            modifier = alignmentModifier,
                            options = cachedFields[num] as TextFieldOptions,
                            focusRequester = nextRequesters[num].first.second,
                            nextFocusRequester = nextRequesters[num].second?.second,
                            isFirstResponder = (cachedFields[num] as TextFieldOptions).firstResponder
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightGray), contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    setScreen(Enter)
                }) {
                    Text("Back")
                }
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class, ExperimentalMaterialApi::class)
@Composable
fun ResultText(options: TextOptions, modifier: Modifier = Modifier, focusRequester: FocusRequester? = null) {
    val fieldValue by remember {
        mutableStateOf(buildAnnotatedString {
            with(options) {
                val index = content.indexOf(link)
                if (index == -1) {
                    withAnnotation(ANNOTATION_TAG_UNDERLINE, annotation = ANNOTATION_IGNORE) {
                        append(content)
                    }
                } else {
                    val beforeLink = content.substring(0 until index)
                    val afterLink = content.substring(index + link.length until content.length)
                    withAnnotation(ANNOTATION_TAG_UNDERLINE, annotation = ANNOTATION_IGNORE) {
                        append(beforeLink)
                    }
                    withAnnotation(ANNOTATION_TAG_LINK_UNDERLINE, annotation = ANNOTATION_IGNORE) {
                        withStyle(
                            SpanStyle(
                                color = if (isLinkColorClear) Transparent else linkColor,
                                fontFamily = linkFont.item,
                                fontSize = linkFontSize
                            )
                        ) {
                            append(link)
                        }
                    }
                    withAnnotation(ANNOTATION_TAG_UNDERLINE, annotation = ANNOTATION_IGNORE) {
                        append(afterLink)
                    }
                }
            }
        })
    }

    var onDraw: DrawScope.() -> Unit by remember { mutableStateOf({}) }
    var onDrawTextUnderline: DrawScope.() -> Unit by remember { mutableStateOf({}) }
    val uriHandler = LocalUriHandler.current

    with(options) {
        Box(
            modifier = modifier
                .then(
                    if (borderWidth != 0.dp) {
                        Modifier.border(
                            BorderStroke(borderWidth, if (isBorderColorClear) Transparent else borderColor),
                            RoundedCornerShape(radius)
                        )
                    } else Modifier
                )
                .then(
                    if (isShadowColorClear) Modifier else {
                        Modifier.shadow(
                            color = shadowColor,
                            opacity = shadowOpacity,
                            radius = shadowRadius,
                            offsetX = shadowOffsetX,
                            offsetY = shadowOffsetY,
                            width = shadowWidth,
                            height = shadowHeight
                        )
                    }
                )
                .clip(RoundedCornerShape(radius))
                .background(if (isBackgroundClear) Transparent else borderColor)
                .padding(borderWidth)
                .focusRequester(focusRequester ?: FocusRequester())
        ) {
            ClickableText(
                text = fieldValue,
                style = TextStyle.Default.copy(
                    fontSize = fontSize,
                    color = if (isTextColorClear) Transparent else textColor,
                    lineHeight = (fontSize.value + lineSpacing.value).sp,
                    textAlign = textAlignment.item,
                    fontFamily = font.item
                ),
                onClick = { charOffset ->
                    if (content.indexOf(link) != -1 && link.isNotBlank() && urlLinkContent.isNotBlank()) {
                        fieldValue.getStringAnnotations(ANNOTATION_TAG_LINK_UNDERLINE, charOffset, charOffset)
                            .firstOrNull()
                            ?.let { span ->
                                var url = Uri.parse(urlLinkContent)
                                if (!urlLinkContent.startsWith("http://") && !urlLinkContent.startsWith("https://")) {
                                    url = Uri.parse("https://$url")
                                }
                                uriHandler.openUri(url.toString())
                            }
                    }
                },
                maxLines = if (lineCount == 0) MAX_VALUE else lineCount,
                overflow = Ellipsis,
                onTextLayout = { layoutResult ->
                    if (content.indexOf(link) != -1) {
                        val annotation =
                            fieldValue.getStringAnnotations(ANNOTATION_TAG_LINK_UNDERLINE, 0, MAX_VALUE)
                                .first()
                        val textBounds = layoutResult.getBoundingBoxes(annotation.start, annotation.end)
                        onDraw = {
                            val isPaddingsNull = paddingLeft == null && paddingRight == null && paddingBottom == null
                            var paddingLeft = paddingLeft?.toPx() ?: 0f
                            if (isPaddingsNull) {
                                paddingLeft = paddingTop.toPx()
                            }
                            val maxRightBound = layoutResult.size.width
                            for (bound in textBounds) {
                                if (bound.right <= maxRightBound) {
                                    val leftX = bound.bottomLeft.x
                                    val leftY = bound.bottomLeft.y
                                    val rightX = bound.bottomRight.x
                                    val rightY = bound.bottomRight.y
                                    drawLine(
                                        color = if (isLinkUnderlineColorClear || linkUnderlineThickness == 0.dp) Transparent else linkUnderlineColor,
                                        strokeWidth = linkUnderlineThickness.toPx(),
                                        start = bound.bottomLeft.copy(leftX + paddingLeft, leftY + paddingTop.toPx()),
                                        end = bound.bottomRight.copy(rightX + paddingLeft, rightY + paddingTop.toPx())
                                    )
                                }
                            }
                        }
                        onDrawTextUnderline = {
                            val isPaddingsNull = paddingLeft == null && paddingRight == null && paddingBottom == null
                            var paddingLeft = paddingLeft?.toPx() ?: 0f
                            if (isPaddingsNull) {
                                paddingLeft = paddingTop.toPx()
                            }
                            val beforeLink = layoutResult.getBoundingBoxes(0, annotation.start)
                            val afterLink = layoutResult.getBoundingBoxes(annotation.end, fieldValue.text.length)
                            onDrawTextUnderline = {
                                for (bound in beforeLink) {
                                    val leftX = bound.bottomLeft.x
                                    val leftY = bound.bottomLeft.y
                                    val rightX = bound.bottomRight.x
                                    val rightY = bound.bottomRight.y
                                    drawLine(
                                        color = if (isUnderlineColorClear || underlineThickness == 0.dp) Transparent else underlineColor,
                                        strokeWidth = underlineThickness.toPx(),
                                        start = bound.bottomLeft.copy(
                                            leftX + paddingLeft,
                                            leftY + paddingTop.toPx()
                                        ),
                                        end = bound.bottomRight.copy(rightX + paddingLeft, rightY + paddingTop.toPx())
                                    )
                                }
                                for (bound in afterLink) {
                                    val leftX = bound.bottomLeft.x
                                    val leftY = bound.bottomLeft.y
                                    val rightX = bound.bottomRight.x
                                    val rightY = bound.bottomRight.y
                                    drawLine(
                                        color = if (isUnderlineColorClear || underlineThickness == 0.dp) Transparent else underlineColor,
                                        strokeWidth = underlineThickness.toPx(),
                                        start = bound.bottomLeft.copy(
                                            leftX + paddingLeft,
                                            leftY + paddingTop.toPx()
                                        ),
                                        end = bound.bottomRight.copy(rightX + paddingLeft, rightY + paddingTop.toPx())
                                    )
                                }
                            }
                        }
                    } else {
                        val textBounds = layoutResult.getBoundingBoxes(0, fieldValue.text.length)
                        onDrawTextUnderline = {
                            var paddingLeft = paddingLeft?.toPx() ?: 0f
                            val paddingRight = paddingRight?.toPx() ?: 0f
                            val paddingBottom = paddingBottom?.toPx() ?: 0f
                            if (paddingLeft + paddingRight + paddingBottom == 0f) {
                                paddingLeft = paddingTop.toPx()
                            }
                            val maxRightBound = layoutResult.size.width
                            for (bound in textBounds) {
                                if (bound.right <= maxRightBound) {
                                    val leftX = bound.bottomLeft.x
                                    val leftY = bound.bottomLeft.y
                                    val rightX = bound.bottomRight.x
                                    val rightY = bound.bottomRight.y
                                    drawLine(
                                        color = if (isUnderlineColorClear || underlineThickness == 0.dp) Transparent else underlineColor,
                                        strokeWidth = underlineThickness.toPx(),
                                        start = bound.bottomLeft.copy(leftX + paddingLeft, leftY + paddingTop.toPx()),
                                        end = bound.bottomRight.copy(rightX + paddingLeft, rightY + paddingTop.toPx())
                                    )
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .background(if (isBackgroundClear) Transparent else borderColor)
                    .clip(RoundedCornerShape(radius))
                    .background(if (isBackgroundClear) Transparent else background)
                    .defaultMinSize(
                        minHeight = if (height == 0.dp) minHeight else Unspecified,
                        minWidth = if (width == 0.dp) minWidth else Unspecified
                    )
                    .then(if (height == 0.dp) Modifier else Modifier.height(height))
                    .then(if (width == 0.dp) Modifier else Modifier.width(width))
                    .verticalScroll(rememberScrollState(), isScrollable)
                    .indicatorLine(
                        enabled = true, isError = false, colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Transparent,
                            focusedIndicatorColor = Transparent,
                            unfocusedIndicatorColor = Transparent,
                            disabledIndicatorColor = Transparent
                        ), interactionSource = MutableInteractionSource()
                    )
                    .drawBehind {
                        onDrawTextUnderline()
                        onDraw()
                    }
                    .padding(
                        if (paddingLeft == null && paddingRight == null && paddingBottom == null) {
                            PaddingValues(paddingTop)
                        } else PaddingValues(
                            paddingLeft ?: 0.dp, paddingTop, paddingRight ?: 0.dp, paddingBottom ?: 0.dp
                        )
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultTextField(
    options: TextFieldOptions,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester?,
    nextFocusRequester: FocusRequester?,
    isFirstResponder: Boolean = false
) {
    LaunchedEffect(Unit) {
        if (isFirstResponder) {
            focusRequester?.requestFocus()
        }
    }

    val onDraw: DrawScope.() -> Unit by remember { mutableStateOf({}) }
    val focusManager = LocalFocusManager.current
    var isEndOfLine by remember { mutableStateOf(false) }
    var onDrawTextUnderline: DrawScope.() -> Unit by remember { mutableStateOf({}) }
    var fieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }
    var isReachedMaxLines by remember { mutableStateOf(false) }

    with(options) {
        Box(
            modifier = modifier
                .then(
                    if (borderWidth != 0.dp) {
                        Modifier.border(
                            BorderStroke(borderWidth, if (isBorderColorClear) Transparent else borderColor),
                            RoundedCornerShape(radius)
                        )
                    } else Modifier
                )
                .then(
                    if (isShadowColorClear) Modifier else {
                        Modifier.shadow(
                            color = shadowColor,
                            opacity = shadowOpacity,
                            radius = shadowRadius,
                            offsetX = shadowOffsetX,
                            offsetY = shadowOffsetY,
                            width = shadowWidth,
                            height = shadowHeight
                        )
                    }
                )
                .clip(RoundedCornerShape(radius))
                .background(if (isBackgroundClear) Transparent else borderColor)
                .padding(
                    borderWidth,
                    borderWidth,
                    borderWidth,
                    borderWidth + (paddingBottom ?: if (paddingLeft == null) paddingTop else 0.dp)
                )

        ) {
            BasicTextField(value = fieldValue,
                onValueChange = {
                    if (fieldValue.text.contains(it.text)) {
                        fieldValue = it.copy(it.text.take(if (maxCharacters == 0) MAX_VALUE else maxCharacters))
                    }
                    if (isReachedMaxLines) return@BasicTextField
                    if (maxCharacters == 0) {
                        fieldValue = it.copy(it.text.take(MAX_VALUE))
                    } else {
                        if (it.text.length < maxCharacters) {
                            fieldValue = it.copy(it.text.take(maxCharacters))
                            return@BasicTextField
                        }
                        if (it.text != fieldValue.text) {
                            fieldValue = it.copy(it.text.take(maxCharacters))
                            if (nextFocusRequester == null) {
                                Log.d("ResultScreen input", fieldValue.text)
                                focusManager.clearFocus()
                            } else {
                                Log.d("ResultScreen input", fieldValue.text)
                                Thread.sleep((executionDelay * 1000).toLong())
                                nextFocusRequester.requestFocus()
                            }
                            return@BasicTextField
                        }
                        fieldValue = it.copy(it.text.take(maxCharacters))
                    }
                },
                textStyle = TextStyle.Default.copy(
                    fontSize = fontSize,
                    color = if (isTextColorClear) Transparent else textColor,
                    lineHeight = (fontSize.value + lineSpacing.value).sp,
                    textAlign = textAlignment.item,
                    fontFamily = font.item
                ),
                visualTransformation = if (secureTextEntry) PasswordVisualTransformation('*') else VisualTransformation.None,
                maxLines = if (lineCount == 0) MAX_VALUE else lineCount,
                decorationBox = { innerTextField ->
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = fieldValue.text,
                        innerTextField = innerTextField,
                        singleLine = false,
                        visualTransformation = if (secureTextEntry) PasswordVisualTransformation('*') else VisualTransformation.None,
                        interactionSource = MutableInteractionSource(),
                        placeholder = {
                            Text(
                                text = defaultText,
                                color = if (isDefaultTextColorClear) Transparent else defaultTextColor,
                                fontFamily = font.item,
                                fontSize = fontSize
                            )
                        },
                        contentPadding = if (paddingLeft == null && paddingRight == null && paddingBottom == null) {
                            PaddingValues(paddingTop)
                        } else PaddingValues(
                            paddingLeft ?: 0.dp, paddingTop, paddingRight ?: 0.dp, paddingBottom ?: 0.dp
                        ),
                        enabled = true
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType.item,
                    autoCorrect = false,
                    imeAction = Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    Log.d("ResultScreen input", fieldValue.text)
                    if (nextFocusRequester == null) {
                        focusManager.clearFocus()
                    } else {
                        Thread.sleep((executionDelay * 1000).toLong())
                        nextFocusRequester.requestFocus()
                    }
                }),
                onTextLayout = { layoutResult ->
                    isReachedMaxLines = layoutResult.lineCount > lineCount && lineCount != 0
                    if (isReachedMaxLines) {
                        fieldValue = fieldValue.copy(fieldValue.text.dropLast(1))
                        Log.d("ResultScreen input", fieldValue.text)
                        if (nextFocusRequester == null) {
                            focusManager.clearFocus()
                        } else {
                            Thread.sleep((executionDelay * 1000).toLong())
                            nextFocusRequester.requestFocus()
                        }
                    }
                    val textBounds = layoutResult.getBoundingBoxes(0, fieldValue.text.length)
                    onDrawTextUnderline = {
                        val isPaddingsNull = paddingLeft == null && paddingRight == null && paddingBottom == null
                        var paddingLeft = paddingLeft?.toPx() ?: 0f
                        if (isPaddingsNull) {
                            paddingLeft = paddingTop.toPx()
                        }
                        val maxRightBound = layoutResult.size.width
                        isEndOfLine = layoutResult.hasVisualOverflow
                        for (bound in textBounds) {
                            if (bound.right <= maxRightBound) {
                                val leftX = bound.bottomLeft.x
                                val leftY = bound.bottomLeft.y
                                val rightX = bound.bottomRight.x
                                val rightY = bound.bottomRight.y
                                drawLine(
                                    color = if (isUnderlineColorClear || underlineThickness == 0.dp) Transparent else underlineColor,
                                    strokeWidth = underlineThickness.toPx(),
                                    start = bound.bottomLeft.copy(leftX + paddingLeft, leftY + paddingTop.toPx()),
                                    end = bound.bottomRight.copy(rightX + paddingLeft, rightY + paddingTop.toPx())
                                )
                            }
                        }
                    }
                },
                modifier = Modifier
                    .background(if (isBackgroundClear) Transparent else borderColor)
                    .clip(RoundedCornerShape(radius))
                    .background(if (isBackgroundClear) Transparent else background)
                    .focusRequester(focusRequester ?: FocusRequester())
                    .defaultMinSize(
                        minHeight = if (height == 0.dp) minHeight else Unspecified,
                        minWidth = if (width == 0.dp) minWidth else Unspecified
                    )
                    .then(
                        if (height == 0.dp || (isFocused && dynamicHeight)) Modifier.defaultMinSize(minHeight = height)
                        else Modifier.height(height)
                    )
                    .then(
                        if (width == 0.dp)
                            Modifier.width(IntrinsicSize.Min)
                        else Modifier.width(width)
                    )
                    .verticalScroll(rememberScrollState(), isScrollable)
                    .indicatorLine(
                        enabled = true, isError = false, colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Transparent,
                            focusedIndicatorColor = Transparent,
                            unfocusedIndicatorColor = Transparent,
                            disabledIndicatorColor = Transparent
                        ), interactionSource = MutableInteractionSource()
                    )
                    .drawBehind {
                        onDrawTextUnderline()
                        onDraw()
                    }
                    .onFocusChanged {
                        isFocused = it.isFocused
                    }
            )
        }
    }
}

@Preview
@Composable
private fun OutputScreenContent_Preview() {
    val options by remember {
        mutableStateOf(
            TextFieldOptions(
                width = 40.dp,
                height = 60.dp,
                underlineThickness = 1.dp,
                paddingTop = 10.dp,
                lineCount = 2
            )
        )
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(White)
    ) {
        ResultTextField(
            modifier = Modifier.offset(100.dp, 100.dp),
            options = options,
            focusRequester = FocusRequester(),
            nextFocusRequester = FocusRequester()
        )
    }
}
