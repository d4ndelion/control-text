package com.dandelion.controltext.data.items

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.dandelion.controltext.R

data class FontItem(
    override val name: String,
    override val item: FontFamily
) : Item<FontFamily>

val steagalFontMedium = FontItem("steagalFontMedium", FontFamily(Font(R.font.steagal_medium)))
val steagalFontRegular = FontItem("steagalFontRegular", FontFamily(Font(R.font.steagal_regular)))

val fonts = listOf(steagalFontRegular, steagalFontMedium)
