package com.example.todoapp.HomeScreen.Data_layer.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface todoDao {
    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun GetallTodos(): Flow<List<TodoItem>>

    @Upsert
    suspend fun addTodos(todo: TodoItem)

    @Delete
    suspend fun Delete(todo: TodoItem)

    @Update
    suspend fun updateTodo(todo: TodoItem)
}