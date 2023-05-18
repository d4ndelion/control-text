package com.dandelion.controltext.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dandelion.controltext.data.items.Item
import com.dandelion.controltext.data.items.fonts
import com.dandelion.controltext.data.items.steagalFontRegular

@Composable
fun <T> ItemDropdown(
    label: String,
    initialItem: Item<T>,
    items: List<Item<T>>,
    onItemClick: (Item<T>) -> Unit,
    item: @Composable (Item<T>) -> Unit
) {

    LaunchedEffect(key1 = Unit, block = { onItemClick(initialItem) })

    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember(onItemClick) { mutableStateOf(initialItem) }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp), text = label
        )
        Spacer(Modifier.height(10.dp))
        Box(
            Modifier
                .fillMaxWidth(.8f)
                .height(30.dp)
                .border(BorderStroke(1.dp, Black), RoundedCornerShape(2.dp))
                .clickable { isExpanded = true }, contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = selectedItem.name,
                style = TextStyle(fontWeight = Bold)
            )
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = false
                }) {
                items.forEach {
                    DropdownMenuItem(onClick = {
                        onItemClick(it)
                        selectedItem = it
                        isExpanded = false
                    }) {
                        item(it)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun ItemDropdown_Preview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(White)
    ) {
        ItemDropdown(label = "asd", items = fonts, onItemClick = {}, initialItem = steagalFontRegular) {
            Text(it.name)
        }
    }
}
