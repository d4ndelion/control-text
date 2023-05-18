package com.dandelion.controltext.data

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.dandelion.controltext.data.items.Item

interface CommonOptions {
    var radius: Dp

    //<editor-fold desc="Position">
    var xOffset: Dp
    var yOffset: Dp
    var relativePosition: Item<Alignment> // xRel yRel
    //</editor-fold>

    //<editor-fold desc="Size">
    var width: Dp
    var minWidth: Dp
    var height: Dp
    var minHeight: Dp
    //</editor-fold>

    //<editor-fold desc="Padding">
    var paddingLeft: Dp?
    var paddingTop: Dp
    var paddingRight: Dp?
    var paddingBottom: Dp?
    //</editor-fold>

    //<editor-fold desc="Border">
    var borderWidth: Dp
    var borderColor: Color
    var isBorderColorClear: Boolean
    //</editor-fold>

    //<editor-fold desc="Background">
    var background: Color
    var isBackgroundClear: Boolean
    var shadowColor: Color
    var isShadowColorClear: Boolean
    var shadowOpacity: Float
    var shadowRadius: Dp
    var shadowOffsetX: Dp
    var shadowOffsetY: Dp
    var shadowWidth: Dp?
    var shadowHeight: Dp?
    //</editor-fold>

    //<editor-fold desc="Text">
    var font: Item<FontFamily>
    var fontSize: TextUnit
    var textColor: Color
    var isTextColorClear: Boolean
    var lineSpacing: TextUnit
    var lineCount: Int
    var isScrollable: Boolean
    var textAlignment: Item<TextAlign>
    //</editor-fold>

    //<editor-fold desc="Underline">
    var underlineThickness: Dp
    var underlineColor: Color
    var isUnderlineColorClear: Boolean
    //</editor-fold>
}
