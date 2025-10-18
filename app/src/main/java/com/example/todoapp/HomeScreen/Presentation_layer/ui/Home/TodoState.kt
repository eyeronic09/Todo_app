package com.example.todoapp.HomeScreen.Presentation_layer.ui.Home

import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem

data class TodoState(
    val todos: List<TodoItem> = emptyList(),
    val newTodoTitle: String = "",
    val isEditing: Boolean = false,
    val currentEditTodo: TodoItem? = null,
    val editTitle: String = "",
    val onToggleDone: Boolean = true,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)