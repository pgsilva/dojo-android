package com.example.dojo.iteractor

import android.app.Application
import com.example.dojo.core.Task
import com.example.dojo.repository.factory.Type
import com.example.dojo.repository.factory.loadRepository
import kotlinx.coroutines.flow.Flow

class FormInteractor(
    private val application: Application
) {

    private val repository by lazy {
        loadRepository(application) { Type.TODO }
    }

    suspend fun add(item: Task, id: String?) {
        if (id != null) repository.upsert(item.copy(id = id))
        else repository.upsert(item)
    }

    suspend fun load(id: String): Flow<Task> {
        return repository.get(id)
    }

    suspend fun giveUp(id: String) {
        repository.delete(id)
    }
}
