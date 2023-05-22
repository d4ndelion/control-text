package com.dandelion.controltext.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType.Companion.NumberPassword
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

@Composable
fun ColorInput(
    label: String,
    initValue: Color,
    defaultClear: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onColorChange: (Color) -> Unit
) {

    var r by remember(onColorChange) {
        mutableStateOf((initValue.red * 255).toInt().toString())
    }
    var g by remember(onColorChange) {
        mutableStateOf((initValue.green * 255).toInt().toString())
    }
    var b by remember(onColorChange) {
        mutableStateOf((initValue.blue * 255).toInt().toString())
    }
    var clear by remember(onColorChange) {
        mutableStateOf(defaultClear)
    }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(), text = label
        )
        LabeledOptionCheckbox(checked = clear, label = "Clear", onCheckedChange = {
            onCheckedChange(it)
            clear = it
            if (clear) {
                onColorChange(Transparent)
                r = "0"; g = "0"; b = "0"
            } else {
                onColorChange(Black)
                r = "0"; g = "0"; b = "0"
            }
        }, labelSize = 14.sp)
        Spacer(Modifier.height(6.dp))
        ColorField(value = r, onValueChange = {
            r = it
            onColorChange(Color(r.toIntOrNull() ?: 0, g.toIntOrNull() ?: 0, b.toIntOrNull() ?: 0))
        }, enabled = !clear)
        Spacer(Modifier.height(10.dp))
        ColorField(value = g, onValueChange = {
            g = it
            onColorChange(Color(r.toIntOrNull() ?: 0, g.toIntOrNull() ?: 0, b.toIntOrNull() ?: 0))
        }, enabled = !clear)
        Spacer(Modifier.height(10.dp))
        ColorField(value = b, onValueChange = {
            b = it
            onColorChange(Color(r.toIntOrNull() ?: 0, g.toIntOrNull() ?: 0, b.toIntOrNull() ?: 0))
        }, enabled = !clear)
    }
}

@Composable
private fun ColorField(value: String, onValueChange: (String) -> Unit, enabled: Boolean = true) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth(.75f)
            .padding(end = 10.dp)
            .border(BorderStroke(1.dp, if (enabled) Black else Gray), RoundedCornerShape(4.dp))
            .padding(start = 6.dp, top = 4.dp, bottom = 4.dp),
        value = value,
        onValueChange = {
            if (it.isDigitsOnly() && it.length <= 3 && (it.toIntOrNull() ?: 0) < 256) {
                onValueChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = NumberPassword),
        enabled = enabled,
        textStyle = TextStyle(color = if (enabled) Black else Gray)
    )
}

@Preview
@Composable
private fun ColorInput_Preview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(White)
    ) {
        ColorInput(label = "asd", initValue = Transparent, onColorChange = {}, defaultClear = false, onCheckedChange = {})
    }
}

