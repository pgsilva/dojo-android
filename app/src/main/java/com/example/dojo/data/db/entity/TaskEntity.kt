package com.example.dojo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME_TASK = "tb_task"

@Entity(tableName = TABLE_NAME_TASK)
data class TaskEntity(
    @PrimaryKey val id: String,
    val coverImageUrl: String?,
    val name: String,
    val label: String,
    val description: String?
)