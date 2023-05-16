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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextOptionField(label: String, initValue: String, onValueChange: (String) -> Unit) {
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
                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(4.dp))
                .padding(start = 6.dp, top = 4.dp, bottom = 4.dp),
            value = value,
            onValueChange = {
                value = it
                onValueChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
            textStyle = TextStyle(fontSize = 18.sp)
        )
    }
}

@Preview
@Composable
private fun TextOptionField_Preview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(White)) {
        TextOptionField("asd", "") {}
    }
}
