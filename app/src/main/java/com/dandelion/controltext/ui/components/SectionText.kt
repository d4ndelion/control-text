package com.dandelion.controltext.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun SectionText(text: String) {
    Text(modifier = Modifier.fillMaxWidth(), text = text, fontSize = 30.sp)
}
