package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todoapp.todolist.Room.Repository.TodoRepository
import com.example.todoapp.todolist.Room.ToDoDatabase
import com.example.todoapp.todolist.TodoScreen
import com.example.todoapp.ui.theme.ToDoAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val db = ToDoDatabase.getDatabase(applicationContext)
            val todoDao = db.todoDao()
            val repository = TodoRepository(todoDao)

            ToDoAppTheme {
               TodoScreen(repository)
            }
        }
    }
}