package com.example.todoapp.HomeScreen.Presentation_layer.ui.Home

import android.util.Log
import androidx.compose.ui.window.isPopupLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem
import com.example.todoapp.HomeScreen.Data_layer.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoState())
    val uiState: StateFlow<TodoState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            repository.getAllTodos().collect { todoItems ->
                _uiState.update { it.copy(todos = todoItems) }
            }
        }
    }

    fun onEvent(event: TodoEvent) {
        when (event) {
            is TodoEvent.OnAddTodo -> {
                val currentTitle = _uiState.value.newTodoTitle
                if (currentTitle.isNotBlank()) {
                    viewModelScope.launch {
                        repository.addTodo(
                            TodoItem(
                                title = currentTitle,
                                isDone = false
                            )
                        )
                        // Clear the input field after adding
                        _uiState.update { it.copy(newTodoTitle = "") }
                    }
                }
            }

            is TodoEvent.OnDeleteTodo -> {
                viewModelScope.launch {
                    repository.deleteTodo(event.todoItem)
                }
            }

            is TodoEvent.OnToggleDone -> {
                viewModelScope.launch {
                    repository.onComplete(event.todoItem.copy(isDone = !event.todoItem.isDone))

                }
            }
            is TodoEvent.OnEditTodo -> {
                viewModelScope.launch {
                    _uiState.update {
                        it.copy(
                            isEditing = true,
                            currentEditTodo = event.todoItem,
                            editTitle = event.todoItem.title
                        )

                    }

                }
                Log.d("new " , _uiState.value.todos.toString())

            }

            TodoEvent.OnCancelEdit -> TODO()
            TodoEvent.OnSaveEdit -> {
                viewModelScope.launch {
                    val currentEditTodo = _uiState.value.currentEditTodo
                    val newTitle = _uiState.value.editTitle

                    val updatedTodo = currentEditTodo!!.copy(title = newTitle)
                    repository.onTitleChange(updatedTodo)

                    _uiState.update {
                        it.copy(
                            isEditing = false,
                            currentEditTodo = null,
                            editTitle = ""
                        )
                    }

                }

            }
            is TodoEvent.OnTitleChange -> {
                _uiState.update {
                    it.copy(newTodoTitle = event.title)
                }
            }

            is TodoEvent.OnUpdateTitle -> {
                _uiState.update {
                    it.copy(
                        isEditing = true,
                        currentEditTodo = _uiState.value.currentEditTodo,
                        editTitle = event.newTitle,

                    )
                }
            }
        }
    }
}