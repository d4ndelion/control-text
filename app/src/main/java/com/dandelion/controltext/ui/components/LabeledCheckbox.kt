package com.dandelion.controltext.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LabeledCheckbox(label: String, checked: Boolean, labelSize: TextUnit = 16.sp, onCheckedChange: (Boolean) -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.padding(start = 10.dp), text = label, fontSize = labelSize)
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun LabeledOptionCheckbox(
    label: String,
    checked: Boolean,
    labelSize: TextUnit = 16.sp,
    onCheckedChange: (Boolean) -> Unit,
) {

    var isChecked by remember(onCheckedChange) { mutableStateOf(checked) }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.padding(start = 10.dp), text = label, fontSize = labelSize)
        Checkbox(checked = isChecked, onCheckedChange = {
            onCheckedChange(it)
            isChecked = it
        })
    }
}

@Preview
@Composable
private fun LabeledCheckbox_Preview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        LabeledCheckbox(checked = false, label = "asd", onCheckedChange = {})
    }
}
