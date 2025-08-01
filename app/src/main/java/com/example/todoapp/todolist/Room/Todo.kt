package com.example.todoapp.todolist.Room

import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo_TB")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    val isDone: Boolean = false
)