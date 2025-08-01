package com.example.todoapp.todolist.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface  TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)
    @Delete
    suspend fun delete(todo: Todo)
    @Update
    suspend fun update(todo: Todo)

    @Query("SELECT * FROM TODO_TB")
    suspend fun getAll(): List<Todo>
}