package com.example.todoapp.HomeScreen.Data_layer.repository

import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoItem>>
    suspend fun addTodo(todo: TodoItem)

    suspend fun onComplete(todo: TodoItem)


    suspend fun deleteTodo(todo: TodoItem)

    suspend fun onTitleChange(todoItem: TodoItem)
}