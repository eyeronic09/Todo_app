package com.example.todoapp.HomeScreen.Presentation_layer.ui.Home

import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem

sealed class  TodoEvent {

    data class OntitleChange(val title : String ) : TodoEvent()
    data class OnToggleDone(val todoItem: TodoItem ) : TodoEvent()
    data class OnDeleteTodo(val todoItem: TodoItem) : TodoEvent()

    data object OnAddTodo : TodoEvent()
}