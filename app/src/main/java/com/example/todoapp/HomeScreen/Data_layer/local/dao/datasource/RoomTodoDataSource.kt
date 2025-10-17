package com.example.todoapp.HomeScreen.Data_layer.local.dao.datasource

import com.example.todoapp.HomeScreen.Data_layer.local.dao.todoDao
import com.example.todoapp.HomeScreen.Data_layer.datasource.TodoDataSource
import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem
import kotlinx.coroutines.flow.Flow

class RoomTodoDataSource(private val dao : todoDao): TodoDataSource {


    override suspend fun add(todo: TodoItem) {
        return dao.addTodos(todo)
    }

    override suspend fun delete(todo: TodoItem) {
      return  dao.Delete(todo)
    }

    override fun getAllTodo(): Flow<List<TodoItem>> {
        return dao.GetallTodos()
    }

}