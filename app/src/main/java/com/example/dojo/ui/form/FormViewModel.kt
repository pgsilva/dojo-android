package com.example.dojo.ui.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dojo.core.Task
import com.example.dojo.core.port.TaskDataManager
import com.example.dojo.repository.factory.TaskDataManagerFactory
import com.example.dojo.repository.factory.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FormViewModel(application: Application) : ViewModel() {

    private val repository by lazy { loadRepository(application) }

    fun add(item: Task, id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (id != null) repository.upsert(item.copy(id = id))
            else repository.upsert(item)
        }
    }

    suspend fun load(id: String): Task {
        return repository.get(id)
    }

    fun delete(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }

    private fun loadRepository(application: Application): TaskDataManager {
        val factory = TaskDataManagerFactory.factory(application)
        return factory.of(Type.TODO)
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    checkNotNull(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                FormViewModel(application)
            }
        }
    }

}