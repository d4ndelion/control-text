package com.dandelion.controltext

import android.os.Bundle
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dandelion.controltext.ui.screens.Screen
import com.dandelion.controltext.ui.screens.Screen.Enter
import com.dandelion.controltext.ui.screens.Screen.Options
import com.dandelion.controltext.ui.screens.Screen.Output
import com.dandelion.controltext.ui.theme.ControlTextTheme

private var screenState by mutableStateOf<Screen>(Enter)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            ControlTextTheme {
                App(screenState)
            }
        }
    }
}

@Composable
fun App(screen: Screen) {
    when (screen) {
        is Enter -> screen.EnterScreen()
        is Options -> screen.OptionsScreen()
        is Output -> screen.OutputScreen()
    }
}

fun setScreen(screen: Screen) {
    screenState = screen
}
