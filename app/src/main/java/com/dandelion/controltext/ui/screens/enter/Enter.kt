package com.dandelion.controltext.ui.screens.enter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dandelion.controltext.data.TextFieldOptions
import com.dandelion.controltext.data.cachedFields
import com.dandelion.controltext.setScreen
import com.dandelion.controltext.ui.screens.Screen.Options

@Composable
fun EnterScreenContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Welcome to the text control application!",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Button(modifier = Modifier.fillMaxWidth(.8f), onClick = {
            val capability = 3
            if (cachedFields.isEmpty()) {
                cachedFields.apply {
                    repeat(capability) {
                        add(TextFieldOptions())
                    }
                }
            }
            setScreen(Options(capability = 3))
        }) {
            Text(modifier = Modifier.padding(vertical = 10.dp), text = "Edit fields")
        }
    }
}

@Preview
@Composable
private fun EnterScreenContent_Preview() {
    EnterScreenContent()
}