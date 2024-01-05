package com.example.dojo.repository

import android.app.Application
import com.example.dojo.core.Task
import com.example.dojo.core.port.TaskDataManager
import com.example.dojo.data.db.AppDatabase
import com.example.dojo.data.db.TaskDao
import com.example.dojo.data.toDomain
import com.example.dojo.data.toEntity
import com.example.dojo.repository.factory.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext


class TaskRepository(
    private val application: Application
) : TaskDataManager {

    //Bad Practice
    private val db: AppDatabase = AppDatabase(application)

    private val dao: TaskDao = db.taskDao()

    override fun originFlow(): Type = Type.TODO

    override suspend fun load(): Flow<List<Task>> {
        return dao.findAll().map { list ->
            //parser method
            list.map { it.toDomain() }
        }
    }

    override suspend fun get(id: String): Task {
        return dao.findById(id).toDomain()
    }

    override suspend fun delete(id: String) {
        dao.delete(id)
    }

    override suspend fun upsert(task: Task) {
        val entity = task.toEntity()
        dao.update(entity)
    }

    override suspend fun destroy() {
        dao.deleteAll()
    }


}