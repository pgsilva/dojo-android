package com.example.dojo.iteractor

import android.app.Application
import com.example.dojo.core.Task
import com.example.dojo.repository.factory.Type
import com.example.dojo.repository.factory.loadRepository
import kotlinx.coroutines.flow.Flow

class SearchInteractor(
    private val application: Application
) {

    private val repository by lazy {
        loadRepository(application) { Type.TODO }
    }

    suspend fun load(): Flow<List<Task>> {
        return repository.load()
    }

    suspend fun done(id: String) {
        repository.delete(id)
    }

}