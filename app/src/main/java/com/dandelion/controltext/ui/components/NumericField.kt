package com.dandelion.controltext.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType.Companion.NumberPassword
import androidx.compose.ui.text.input.KeyboardType.Companion.Phone
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.dandelion.controltext.util.isNegativeNumber

@Composable
fun NumericField(label: String, initValue: Dp, onValueChange: (Int) -> Unit) {
    NumericFieldContent(label = label, initValue = initValue.value.toInt().toString(), onValueChange = onValueChange)
}

@Composable
fun NumericField(label: String, initValue: TextUnit, onValueChange: (Int) -> Unit) {
    NumericFieldContent(label = label, initValue = initValue.value.toInt().toString(), onValueChange = onValueChange)
}

@Composable
fun NumericField(label: String, initValue: Int, onValueChange: (Int) -> Unit) {
    NumericFieldContent(label = label, initValue = initValue.toString(), onValueChange = onValueChange)
}

@Composable
fun FloatNumericField(label: String, initValue: Float, isLimited: Boolean = false, onValueChange: (Float) -> Unit) {
    var value by remember(key1 = onValueChange) {
        mutableStateOf(initValue.toString())
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.padding(start = 10.dp), text = label)
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(.75f)
                .padding(end = 10.dp)
                .border(BorderStroke(1.dp, Black), RoundedCornerShape(4.dp))
                .padding(start = 6.dp, top = 4.dp, bottom = 4.dp),
            value = value,
            onValueChange = {
                if (it.length <= 6) {
                    if (isLimited) {
                        value = when (it.toFloatOrNull()) {
                            null -> if (it.isEmpty()) "" else value
                            else -> if (it.toFloat() <= 1) it else ""
                        }
                        onValueChange(value.toFloatOrNull() ?: 0f)
                    } else {
                        value = when (it.toFloatOrNull()) {
                            null -> if (it.isEmpty()) "" else value
                            else -> it
                        }
                        onValueChange(value.toFloatOrNull() ?: 0f)
                    }
                }
            }, keyboardOptions = KeyboardOptions(keyboardType = Phone), textStyle = TextStyle(fontSize = 18.sp)
        )
    }
}

@Composable
fun NegativeNumericField(label: String, initValue: Dp, onValueChange: (Int) -> Unit) {
    var value by remember(key1 = onValueChange) {
        mutableStateOf(initValue.value.toInt().toString())
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.padding(start = 10.dp), text = label)
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(.75f)
                .padding(end = 10.dp)
                .border(BorderStroke(1.dp, Black), RoundedCornerShape(4.dp))
                .padding(start = 6.dp, top = 4.dp, bottom = 4.dp),
            value = value,
            onValueChange = {
                if (it.isNegativeNumber() && it.length <= 6) {
                    value = it
                    onValueChange(value.toIntOrNull() ?: 0)
                }
            }, keyboardOptions = KeyboardOptions(keyboardType = Phone), textStyle = TextStyle(fontSize = 18.sp)
        )
    }
}

@Composable
fun NumericFieldNullable(label: String, initValue: Dp?, onValueChange: (Int?) -> Unit) {

    var value by remember(key1 = onValueChange) {
        mutableStateOf((initValue?.value?.toInt() ?: "").toString())
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.padding(start = 10.dp), text = label)
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(.75f)
                .padding(end = 10.dp)
                .border(BorderStroke(1.dp, Black), RoundedCornerShape(4.dp))
                .padding(start = 6.dp, top = 4.dp, bottom = 4.dp),
            value = value,
            onValueChange = {
                if (it.isDigitsOnly() && it.length <= 6) {
                    value = it
                    onValueChange(value.toIntOrNull())
                }
            }, keyboardOptions = KeyboardOptions(keyboardType = NumberPassword), textStyle = TextStyle(fontSize = 18.sp)
        )
    }
}

@Composable
private fun NumericFieldContent(label: String, initValue: String, onValueChange: (Int) -> Unit) {
    var value by remember(key1 = onValueChange) {
        mutableStateOf(initValue)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.padding(start = 10.dp), text = label)
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(.75f)
                .padding(end = 10.dp)
                .border(BorderStroke(1.dp, Black), RoundedCornerShape(4.dp))
                .padding(start = 6.dp, top = 4.dp, bottom = 4.dp),
            value = value,
            onValueChange = {
                if (it.isDigitsOnly() && it.length <= 6) {
                    value = it
                    onValueChange(value.toIntOrNull() ?: 0)
                }
            }, keyboardOptions = KeyboardOptions(keyboardType = NumberPassword), textStyle = TextStyle(fontSize = 18.sp)
        )
    }
}

@Preview
@Composable
fun NumericField_Preview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(White)
    ) {
        NumericField(label = "asd", initValue = 123.dp, onValueChange = {})
        NumericFieldNullable(label = "asd", initValue = null, onValueChange = {})
    }
}
