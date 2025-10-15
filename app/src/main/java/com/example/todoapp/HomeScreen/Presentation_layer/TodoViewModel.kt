package com.example.todoapp.HomeScreen.Presentation_layer

import androidx.lifecycle.ViewModel
import com.example.todoapp.HomeScreen.TodoItem
import com.example.todoapp.HomeScreen.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoState())
    val state = _uiState.asStateFlow()

    init {
        repository.getAllTodos().onEach { items ->
            _uiState.value.copy(todo = items)
        }
    }


    suspend fun onEvent(event: TodoEvent) {
        when (event) {
            is TodoEvent.AddTodo -> {
                if (_uiState.value.newTodoTittle.isNotBlank()){
                    repository.add(TodoItem(title = _uiState.value.newTodoTittle , isDone = false))
                }
            }

            is TodoEvent.DeleteTodo -> {
                repository.delete(event.todo )
            }
            is TodoEvent.updateTittle -> {
                _uiState.value = _uiState.value.copy(newTodoTittle = event.title)
            }
        }
    }
}