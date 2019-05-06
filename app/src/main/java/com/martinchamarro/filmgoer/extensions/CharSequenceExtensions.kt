package com.martinchamarro.filmgoer.extensions


const val EMPTY = ""

inline fun <R> CharSequence?.letIfNotNullNorEmpty(block: (String) -> R) {
    if (!isNullOrEmpty()) {
        block(this as String)
    }
}

fun CharSequence?.orEmpty() = this ?: EMPTY