package com.example.dojo.ui.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dojo.core.Task
import com.example.dojo.core.port.TaskDataManager
import com.example.dojo.repository.factory.TaskDataManagerFactory
import com.example.dojo.repository.factory.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SearchViewModel(application: Application) : ViewModel() {

    private val repository by lazy { loadRepository(application) }

    // This is a mutable state flow that will be used internally in the viewmodel, empty list is given as initial value.
    private val _todosList = MutableStateFlow(emptyList<Task>())

    //Immutable state flow that you expose to your UI
    val todosList = _todosList.asStateFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            repository.load().flowOn(Dispatchers.IO)
                .collect { list ->
                    _todosList.update { list }
                }
        }
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