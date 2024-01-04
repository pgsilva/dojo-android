package com.example.dojo.data

import com.example.dojo.core.Task
import com.example.dojo.data.db.entity.TaskEntity

internal fun TaskEntity.toDomain(): Task =
    Task(
        this.id,
        this.coverImageUrl,
        this.name,
        this.label,
        this.description
    )

internal fun Task.toEntity(): TaskEntity =
    TaskEntity(
        this.id,
        this.coverImageUrl,
        this.name,
        this.label,
        this.description
    )
