package com.example.dojo.ui.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dojo.core.Task
import com.example.dojo.core.port.TaskDataManager
import com.example.dojo.repository.factory.TaskDataManagerFactory
import com.example.dojo.repository.factory.Type
import kotlinx.coroutines.flow.Flow


class SearchViewModel(application: Application) : ViewModel() {

    private val repository by lazy { loadRepository(application) }


    suspend fun loadTodos(): List<Task> {
        return repository.load()
    }

    private fun loadRepository(application: Application): TaskDataManager {
        val factory = TaskDataManagerFactory.factory(application)
        return factory.of(Type.TODO)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                SearchViewModel(application)
            }
        }
    }

}