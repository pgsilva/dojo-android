package com.example.dojo.core.port

import com.example.dojo.core.Task
import com.example.dojo.repository.factory.Type

interface TaskDataManager {

    fun originFlow(): Type

    fun load(): List<Task>

    fun get(id: String): Task

    fun delete(id: String)

    fun upsert(task: Task)

    fun destroy()

}