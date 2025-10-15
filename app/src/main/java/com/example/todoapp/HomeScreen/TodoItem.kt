package com.example.todoapp.HomeScreen

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todos")
data class TodoItem(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val isDone: Boolean
)