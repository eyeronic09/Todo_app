package com.example.todoapp.todolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.todolist.Room.Repository.TodoRepository
import com.example.todoapp.todolist.Room.Todo


@Composable
fun TodoScreen(repository: TodoRepository) {
    val factory = remember { TodoVMFactory(repository) }
    val viewModel: todoVM = viewModel(factory = factory)
    val todos by viewModel.todoList.collectAsState()
    val editingTodo by viewModel.editingTodo.collectAsState()
    val todoTitle by viewModel.text.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = todoTitle,
            onValueChange = { viewModel.updateTodoText(it) },
            label = { Text("enter your task") }
        )

        Button(onClick = {
            if (todoTitle.isEmpty()) {
                return@Button
            }
            viewModel.addTask(Todo(title = todoTitle, isDone = false) ) }) {
            Text(text = "Add")
        }
        LazyColumn {
            items(todos) { todo ->
                OutlinedCard(modifier = Modifier.padding(8.dp)) {
                    Text(text = todo.title)
                    IconButton(onClick = { viewModel.updateTask(todo) }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    }
                }
            }
        }



    }
}