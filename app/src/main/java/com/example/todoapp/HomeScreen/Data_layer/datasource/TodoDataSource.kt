package com.example.todoapp.HomeScreen.Data_layer.datasource

import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoDataSource {
    suspend fun add(todo : TodoItem)
    suspend fun delete(todo : TodoItem)
    fun getAllTodo() : Flow<List<TodoItem>>

    suspend fun onToggle(todo : TodoItem)
}