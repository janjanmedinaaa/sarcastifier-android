package com.medina.juanantonio.sarcastifier.common.extensions

fun String.sarcastify(): String {
    var result = ""

    forEachIndexed { index, char ->
        result += if (index % 2 == 0) char.toLowerCase() else char.toUpperCase()
    }

    return result
}

fun getEmojiByUnicode(unicode: Int) = String(Character.toChars(unicode))