package com.example.todoapp.HomeScreen.Presentation_layer

import com.example.todoapp.HomeScreen.TodoItem

sealed class TodoEvent {
    data class updateTittle(val title : String) : TodoEvent()
    object AddTodo : TodoEvent()
    data class DeleteTodo(val todo : TodoItem) : TodoEvent()
}