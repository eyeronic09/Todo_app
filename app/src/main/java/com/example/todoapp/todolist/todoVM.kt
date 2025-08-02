package com.example.todoapp.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.todolist.Room.Repository.TodoRepository
import com.example.todoapp.todolist.Room.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class todoVM(private val TodoRepository: TodoRepository): ViewModel() {
    private val _todoList = MutableStateFlow<List<Todo>>(emptyList())// MutableStateFlow<List<Todo>>
    val todoList: StateFlow<List<Todo>> = _todoList.asStateFlow()

    init {
        viewModelScope.launch {
            TodoRepository.getAll().collectLatest {
                _todoList.value = it
            }
        }
    }


}