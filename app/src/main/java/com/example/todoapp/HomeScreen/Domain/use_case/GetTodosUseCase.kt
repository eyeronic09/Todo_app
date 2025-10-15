package com.example.todoapp.HomeScreen.Domain.use_case

import com.example.todoapp.HomeScreen.TodoItem
import com.example.todoapp.HomeScreen.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodosUseCase(private val repository: TodoRepository) {
    operator fun invoke() : Flow<List<TodoItem>> = repository.getAllTodos()
}