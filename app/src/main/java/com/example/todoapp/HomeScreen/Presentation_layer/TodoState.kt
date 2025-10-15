package com.example.todoapp.HomeScreen.Presentation_layer

import com.example.todoapp.HomeScreen.TodoItem

data class TodoState(
    val todo : List<TodoItem> = emptyList(),
    val newTodoTittle : String = ""
)