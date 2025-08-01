package com.example.todoapp.todolist.Room.Repository

import com.example.todoapp.todolist.Room.Todo
import com.example.todoapp.todolist.Room.TodoDao
import kotlinx.coroutines.flow.Flow

interface Repository{
    fun getAll(): Flow<List<Todo>>
    suspend fun insert(todo: Todo)
    suspend fun delete(todo: Todo)
    suspend fun update(todo: Todo)

}


class TodoRepository(private val todoDao: TodoDao): Repository {

}