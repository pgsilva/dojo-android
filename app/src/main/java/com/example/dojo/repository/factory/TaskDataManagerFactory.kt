package com.example.dojo.repository.factory

import android.app.Application
import com.example.dojo.core.port.TaskDataManager
import com.example.dojo.repository.TaskRepository
import java.util.Collections

enum class Type { TODO }

class TaskDataManagerFactory(implementations: Set<TaskDataManager>) {
    private val strategies: MutableMap<Type, TaskDataManager>

    init {
        val tempStrategies = HashMap<Type, TaskDataManager>()
        implementations.forEach { tempStrategies[it.originFlow()] = it }
        strategies = Collections.unmodifiableMap(tempStrategies)
    }

    fun of(type: Type): TaskDataManager =
        strategies.getOrElse(type) {
            throw NoSuchMethodException("data manager not implemented")
        }

    companion object {
        fun factory(application: Application): TaskDataManagerFactory {
            val implementations = setOf(
                TaskRepository(application)
            )

            return TaskDataManagerFactory(implementations)
        }
    }
}