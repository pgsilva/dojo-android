package com.example.dojo.domain.search

import java.net.URL

data class SearchItem(
    val avatarUrl: String?,
    val fullName: String,
    val username: String,
    val description: String,
)

fun String?.isNotValidURL(): Boolean {
    return try {
        URL(this)
        false
    } catch (e: Exception) {
        true
    }
}