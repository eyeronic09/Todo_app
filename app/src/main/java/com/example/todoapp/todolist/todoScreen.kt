package com.example.todoapp.todolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
    val todoTitle by viewModel.text.collectAsState()
    val editingTodo by viewModel.editingTodo.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Input field
        OutlinedTextField(
            value = todoTitle,
            onValueChange = { viewModel.updateTodoText(it) },
            label = { Text(if (editingTodo != null) "Edit Task" else "Add New Task") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Save/Update button
        Button(
            onClick = {
                if (todoTitle.isNotBlank()) {
                    viewModel.saveOrUpdateTask()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (editingTodo != null) "Update" else "Add")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Todo list
        LazyColumn {
            items(todos) { todo ->
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = todo.title,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { viewModel.startEditing(todo) }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}