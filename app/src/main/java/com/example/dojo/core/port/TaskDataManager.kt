package com.example.dojo.core.port

import com.example.dojo.core.Task
import com.example.dojo.repository.factory.Type
import kotlinx.coroutines.flow.Flow

interface TaskDataManager {

    fun originFlow(): Type

    suspend fun load(): Flow<List<Task>>

    suspend fun get(id: String): Flow<Task?>

    suspend fun delete(id: String)

    suspend fun upsert(task: Task)

    suspend fun destroy()

}