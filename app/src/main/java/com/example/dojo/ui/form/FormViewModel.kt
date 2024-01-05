package com.example.dojo.ui.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dojo.core.Task
import com.example.dojo.core.port.TaskDataManager
import com.example.dojo.iteractor.FormInteractor
import com.example.dojo.iteractor.SearchInteractor
import com.example.dojo.repository.factory.TaskDataManagerFactory
import com.example.dojo.repository.factory.Type
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FormViewModel(application: Application) : ViewModel() {

    private val interactor by lazy { FormInteractor(application) }

    fun add(item: Task, id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.add(item, id)
        }
    }

    suspend fun load(id: String): Flow<Task> {
        return interactor.load(id)
    }

    fun delete(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.giveUp(id)
        }
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