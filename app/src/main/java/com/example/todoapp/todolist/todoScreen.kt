package com.example.todoapp.todolist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.todolist.Room.Repository.TodoRepository

@Composable
fun TodoScreen(repository: TodoRepository) {

    val factory = remember { TodoVMFactory(repository) }
    val viewModel: todoVM = viewModel(factory = factory)
    val todos by viewModel.todoList.collectAsState()






}