package com.example.todoapp.todolist.Room


import androidx.room.Database

@Database(entities = [Todo::class], version = 1 , exportSchema = false)
abstract class ToDoDatabase {
    abstract fun todoDao(): TodoDao
}