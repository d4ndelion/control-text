package com.dandelion.controltext.data.items

import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Alignment.Companion.TopStart

class AlignmentItem(override val name: String, override val item: Alignment) : Item<Alignment>

val topStart = AlignmentItem("topStart", TopStart)
val topCenter = AlignmentItem("topCenter", TopCenter)
val topEnd = AlignmentItem("topEnd", TopEnd)
val centerStart = AlignmentItem("centerStart", CenterStart)
val centerMiddle = AlignmentItem("center", Center)
val centerEnd = AlignmentItem("centerEnd", CenterEnd)
val bottomStart = AlignmentItem("bottomStart", BottomStart)
val bottomCenter = AlignmentItem("bottomCenter", BottomCenter)
val bottomEnd = AlignmentItem("bottomEnd", BottomEnd)

val alignments = listOf(
    topStart,
    topCenter,
    topEnd,
    centerStart,
    centerMiddle,
    centerEnd,
    bottomStart,
    bottomCenter,
    bottomEnd
)
