package com.example.todoapp.HomeScreen

data class TodoListContract (
        val todos: List<TodoItem> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
)

data class TodoListEvent (
    val todos: List<TodoItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
