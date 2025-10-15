package com.example.todoapp.HomeScreen

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImp(private val dao: todoDao) : TodoRepository{
    override fun getAllTodos(): Flow<List<TodoItem>> {
        return dao.GetallTodos()
    }

    override suspend fun add(todo: TodoItem) {
        return dao.addTodos(todo)
    }

    override suspend fun delete(todo: TodoItem) {
        return dao.Delete(todo)
    }

}