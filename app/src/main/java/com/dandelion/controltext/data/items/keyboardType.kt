package com.dandelion.controltext.data.items

import androidx.compose.ui.text.input.KeyboardType

data class KeyboardItem(
    override val name: String,
    override val item: KeyboardType
) : Item<KeyboardType>

val text = KeyboardItem("Text", KeyboardType.Ascii)
val number = KeyboardItem("NumberPassword", KeyboardType.NumberPassword)
val email = KeyboardItem("Email", KeyboardType.Email)
val phone = KeyboardItem("Phone", KeyboardType.Phone)

val keyboardTypes = listOf(text, number, email, phone)
