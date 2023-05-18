package com.dandelion.controltext.data.items

import androidx.compose.ui.text.style.TextAlign

data class TextAlignmentItem(override val name: String, override val item: TextAlign) : Item<TextAlign>

val left = TextAlignmentItem("left", TextAlign.Start)
val center = TextAlignmentItem("center", TextAlign.Center)
val right = TextAlignmentItem("right", TextAlign.End)

val textAlignments = listOf(left, center, right)
