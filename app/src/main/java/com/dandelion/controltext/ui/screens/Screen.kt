package com.dandelion.controltext.ui.screens

import androidx.compose.runtime.Composable
import com.dandelion.controltext.ui.screens.enter.EnterScreenContent
import com.dandelion.controltext.ui.screens.options.OptionsScreenContent
import com.dandelion.controltext.ui.screens.output.OutputScreenContent

sealed class Screen {
    object Enter : Screen() {
        @Composable
        fun EnterScreen() = EnterScreenContent()
    }

    class Options(private val capability: Int) : Screen() {
        @Composable
        fun OptionsScreen() = OptionsScreenContent(capability)
    }

    object Output : Screen() {
        @Composable
        fun OutputScreen() = OutputScreenContent()
    }
}
