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
import kotlinx.coroutines.withContext


class TaskRepository(
    private val application: Application
) : TaskDataManager {

    //Bad Practice
    private val db: AppDatabase = AppDatabase(application)

    private val dao: TaskDao = db.taskDao()

    override fun originFlow(): Type = Type.TODO

    override suspend fun load(): List<Task> {
        return withContext(Dispatchers.IO) {
            val taskEntities = dao.findAll()
            taskEntities.map { it.toDomain() }
        }
    }

    override suspend fun get(id: String): Task {
        return withContext(Dispatchers.IO) {
            dao.findById(id).toDomain()
        }
    }

    override suspend fun delete(id: String) {
        withContext(Dispatchers.IO) {
            dao.delete(id)
        }
    }

    override suspend fun upsert(task: Task) {
        withContext(Dispatchers.IO) {
            val entity = task.toEntity()
            dao.update(entity)
        }

    }

    override suspend fun destroy() {
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
    }


}