package com.example.todoapp.HomeScreen

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow


@Dao
interface todoDao {
    fun GetallTodos(): Flow<List<TodoItem>>


    @Insert
    fun addTodos(todo: TodoItem)

    @Delete
    fun Delete(todo: TodoItem)
}