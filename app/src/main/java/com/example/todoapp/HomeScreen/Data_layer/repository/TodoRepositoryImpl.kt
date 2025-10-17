package com.example.todoapp.HomeScreen.Data_layer.repository

import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem
import com.example.todoapp.HomeScreen.Data_layer.datasource.TodoDataSource
import com.example.todoapp.HomeScreen.Data_layer.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(private val dataSource : TodoDataSource) : TodoRepository {
    override fun getAllTodos(): Flow<List<TodoItem>> {
       return dataSource.getAllTodo()
    }

    override suspend fun addTodo(todo: TodoItem) {
        return dataSource.add(todo)
    }

    override suspend fun deleteTodo(todo: TodoItem) {
        return dataSource.delete(todo)
    }

}