package com.example.todoapp.HomeScreen

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoItem>>
    suspend fun add(todo : TodoItem)
    suspend fun delete(todo : TodoItem)
}