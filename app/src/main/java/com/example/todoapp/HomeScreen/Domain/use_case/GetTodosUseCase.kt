package com.example.todoapp.HomeScreen.Domain.use_case

import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem
import com.example.todoapp.HomeScreen.Data_layer.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodosUseCase(private val repository: TodoRepository) {
    operator fun invoke() : Flow<List<TodoItem>> = repository.getAllTodos()
}