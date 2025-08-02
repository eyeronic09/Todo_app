package com.example.todoapp.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.todolist.Room.Repository.TodoRepository

class TodoVMFactory(private val repository: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(todoVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return todoVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}