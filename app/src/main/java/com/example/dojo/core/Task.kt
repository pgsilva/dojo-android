package com.example.dojo.core

import java.util.UUID

data class Task(
    val id: String,
    val coverImageUrl: String?,
    val name: String,
    val label: String,
    val description: String?,
) {
    constructor(
        coverImageUrl: String?,
        name: String,
        label: String,
        description: String?
    ) : this(
        UUID.randomUUID().toString(),
        coverImageUrl,
        name,
        label,
        description,
    )
}
