package com.dandelion.controltext.ui.screens.options

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelion.controltext.data.CommonOptions
import com.dandelion.controltext.data.TextFieldOptions
import com.dandelion.controltext.data.TextOptions
import com.dandelion.controltext.data.items.alignments
import com.dandelion.controltext.data.cachedFields
import com.dandelion.controltext.data.items.fonts
import com.dandelion.controltext.data.items.keyboardTypes
import com.dandelion.controltext.data.items.textAlignments
import com.dandelion.controltext.data.toTextFieldOptions
import com.dandelion.controltext.data.toTextOptions
import com.dandelion.controltext.setScreen
import com.dandelion.controltext.ui.components.ColorInput
import com.dandelion.controltext.ui.components.FloatNumericField
import com.dandelion.controltext.ui.components.ItemDropdown
import com.dandelion.controltext.ui.components.LabeledCheckbox
import com.dandelion.controltext.ui.components.LabeledOptionCheckbox
import com.dandelion.controltext.ui.components.NegativeNumericField
import com.dandelion.controltext.ui.components.NumericField
import com.dandelion.controltext.ui.components.NumericFieldNullable
import com.dandelion.controltext.ui.components.SectionText
import com.dandelion.controltext.ui.components.TextOptionField
import com.dandelion.controltext.ui.screens.Screen.Output
import kotlinx.coroutines.launch

@Composable
fun OptionsScreenContent(fieldCount: Int) {

    val keyboardController = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var fieldIndex by remember { mutableStateOf(0) }
    var isInputEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(fieldIndex) {
        when (cachedFields[fieldIndex]) {
            is TextFieldOptions -> isInputEnabled = true
            is TextOptions -> isInputEnabled = false
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Field ${fieldIndex + 1} options", fontSize = 36.sp, style = TextStyle(fontWeight = Bold))
        LabeledCheckbox(checked = isInputEnabled, label = "Enabled edit", onCheckedChange = {
            isInputEnabled = it
            if (isInputEnabled) {
                cachedFields[fieldIndex] = (cachedFields[fieldIndex] as TextOptions).toTextFieldOptions()
            } else {
                cachedFields[fieldIndex] = (cachedFields[fieldIndex] as TextFieldOptions).toTextOptions()
            }
        })
        Configuration(cachedFields[fieldIndex])
        Button({
            Log.d(
                "Field number ${fieldIndex + 1}",
                "Field number ${fieldIndex + 1}${cachedFields[fieldIndex]}"
            )
            keyboardController.clearFocus()
            coroutineScope.launch {
                scrollState.animateScrollTo(0)
            }
            if (fieldIndex == fieldCount - 1) {
                setScreen(Output)
            } else fieldIndex++
        }) {
            Text(if (fieldIndex == fieldCount - 1) "Go" else "Next")
        }
    }
}

@Composable
private fun Configuration(currentField: CommonOptions) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        with(currentField) {
            SectionText(text = "Position")
            NumericField("Radius", radius) { radius = it.dp }
            NegativeNumericField("X offset", xOffset) { xOffset = it.dp }
            NegativeNumericField("Y offset", yOffset) { yOffset = it.dp }
            ItemDropdown(
                label = "Relative position",
                initialItem = relativePosition,
                items = alignments,
                onItemClick = { relativePosition = it },
                item = { Text(it.name) })
            SectionText(text = "Size")
            NumericField("Width", width) { width = it.dp }
            NumericField("Min width", minWidth) { minWidth = it.dp }
            NumericField("Height", height) { height = it.dp }
            NumericField("Min height", minHeight) { minHeight = it.dp }
            SectionText(text = "Padding (top, right, bottom, left)")
            NumericField("Padding top", paddingTop) { paddingTop = it.dp }
            NumericFieldNullable("Padding right", paddingRight) { paddingRight = it?.dp }
            NumericFieldNullable("Padding bottom", paddingBottom) { paddingBottom = it?.dp }
            NumericFieldNullable("Padding left", paddingLeft) { paddingLeft = it?.dp }
            SectionText(text = "Border")
            NumericField("Border width", borderWidth) { borderWidth = it.dp }
            ColorInput("Border color", borderColor, isBorderColorClear, { isBorderColorClear = it }) {
                borderColor = it
            }
            SectionText(text = "Background")
            ColorInput("Background color", background, isBackgroundClear, { isBackgroundClear = it }) {
                background = it
            }
            SectionText(text = "Shadow")
            ColorInput("Shadow color", shadowColor, isShadowColorClear, { isShadowColorClear = it }) {
                shadowColor = it
            }
            FloatNumericField("Shadow opacity", shadowOpacity, true) { shadowOpacity = it }
            NumericField("Shadow radius", shadowRadius) { shadowRadius = it.dp }
            NegativeNumericField("Shadow offset x", shadowOffsetX) { shadowOffsetX = it.dp }
            NegativeNumericField("Shadow offset y", shadowOffsetY) { shadowOffsetY = it.dp }
            NumericFieldNullable("Shadow width", shadowWidth) { shadowWidth = it?.dp }
            NumericFieldNullable("Shadow height", shadowHeight) { shadowHeight = it?.dp }
            SectionText(text = "Text")
            ItemDropdown(
                label = "Text font",
                initialItem = font,
                items = fonts,
                onItemClick = { font = it },
                item = { Text(it.name) })
            NumericField("Font size", fontSize) { fontSize = it.sp }
            ColorInput("Text color", textColor, isTextColorClear, { isTextColorClear = it }) { textColor = it }
            NumericField("Line spacing", lineSpacing) { lineSpacing = it.sp }
            NumericField("Line count", lineCount) { lineCount = it }
            LabeledOptionCheckbox("Scrollable", isScrollable) { isScrollable = it }
            ItemDropdown(
                label = "textAlignment",
                initialItem = textAlignment,
                items = textAlignments,
                onItemClick = { textAlignment = it },
                item = { Text(it.name) })
            SectionText(text = "Underline")
            NumericField("Underline Thickness", underlineThickness) { underlineThickness = it.dp }
            ColorInput(
                "Underline color",
                underlineColor,
                isUnderlineColorClear,
                { isUnderlineColorClear = it }) { underlineColor = it }
            SectionText(text = "Features")
            when (this) {
                is TextOptions -> TextFeatures(this)
                is TextFieldOptions -> TextFieldFeatures(this)
            }
        }
    }
}

@Composable
fun TextFeatures(currentField: TextOptions) {
    with(currentField) {
        TextOptionField("Content", content) { content = it }
        TextOptionField("Link content", link) { link = it }
        TextOptionField("Link content URL", urlLinkContent) { urlLinkContent = it }
        ColorInput("Link color", linkColor, isLinkColorClear, { isLinkColorClear = it }) { linkColor = it }
        ItemDropdown(
            label = "Link font",
            initialItem = linkFont,
            items = fonts,
            onItemClick = { linkFont = it },
            item = { Text(it.name) })
        NumericField("Link font size", linkFontSize) { linkFontSize = it.sp }
        ColorInput(
            "Link underline color",
            linkUnderlineColor,
            isLinkUnderlineColorClear,
            { isLinkUnderlineColorClear = it }) { linkUnderlineColor = it }
        NumericField("Link underline thickness", linkUnderlineThickness) { linkUnderlineThickness = it.dp }
    }
}

@Composable
fun TextFieldFeatures(currentField: TextFieldOptions) {
    with(currentField) {
        LabeledOptionCheckbox("Dynamic height", dynamicHeight) { dynamicHeight = it }
        TextOptionField("Default text", defaultText) { defaultText = it }
        ColorInput(
            "Default text color",
            defaultTextColor,
            isDefaultTextColorClear,
            { isDefaultTextColorClear = it }) { defaultTextColor = it }
        NumericField("Max characters", maxCharacters) { maxCharacters = it }
        LabeledOptionCheckbox("Secure text entry", secureTextEntry) { secureTextEntry = it }
        ItemDropdown(
            label = "Keyboard type",
            initialItem = keyboardType,
            items = keyboardTypes,
            onItemClick = { keyboardType = it },
            item = { Text(it.name) })
        FloatNumericField("Execution delay", executionDelay) { executionDelay = it }
        TextOptionField("Identifier", identifier) { identifier = it }
        TextOptionField("Next responder", nextResponder) { nextResponder = it }
        LabeledOptionCheckbox("First responder", firstResponder) { firstResponder = it }
    }
}

@Preview
@Composable
private fun OptionsScreenContent_Preview() {
    OptionsScreenContent(3)
}
