package com.example.dojo.repository

import android.app.Application
import com.example.dojo.core.Task
import com.example.dojo.core.port.TaskDataManager
import com.example.dojo.data.db.AppDatabase
import com.example.dojo.data.db.TaskDao
import com.example.dojo.data.toDomain
import com.example.dojo.data.toEntity
import com.example.dojo.repository.factory.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TaskRepository(
    private val application: Application
) : TaskDataManager {

    //Bad Practice
    private val db: AppDatabase = AppDatabase(application)

    private val dao: TaskDao = db.taskDao()

    override fun originFlow(): Type = Type.TODO

    override fun load(): List<Task> {
        val taskEntities = dao.findAll()
        return taskEntities.map { it.toDomain() }
    }

    override fun get(id: String): Task {
        return dao.findById(id).toDomain()

    }

    override fun delete(id: String) {
        dao.delete(id)

    }

    override fun upsert(task: Task) {
        val entity = task.toEntity()
        dao.update(entity)

    }

    override fun destroy() {
        dao.deleteAll()
    }


}