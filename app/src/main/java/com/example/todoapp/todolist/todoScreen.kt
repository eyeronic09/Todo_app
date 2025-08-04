    package com.example.todoapp.todolist

import android.util.Log
import android.widget.CheckBox
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.todolist.Room.Repository.TodoRepository
import com.example.todoapp.todolist.Room.Todo
import com.example.todoapp.todolist.component.CardItem


    @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(repository: TodoRepository) {
    val factory = remember  { TodoVMFactory(repository) }
    val viewModel: todoVM = viewModel(factory = factory)
    val todos by viewModel.todoList.collectAsState()
    val todoTitle by viewModel.text.collectAsState()
    val editingTodo by viewModel.editingTodo.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo List") }
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Row {
                TextField(
                    value = todoTitle,
                    onValueChange = { viewModel.updateTodoText(it) },
                    label = { Text("enter your task") }
                )

                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        if (editingTodo != null) {
                            viewModel.saveOrUpdateTask()
                        }else{
                            viewModel.addTask(Todo(title = todoTitle))
                        }
                    },
                ) {
                    Text(if (editingTodo != null) "Update" else "Add")
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Log.d("TodoScreen", "editingTodo: $editingTodo")

            LazyColumn {
                items(todos) { todo ->
                    CardItem(
                        todo = todo,
                        onDelete = { viewModel.deleteTask(todo) },
                        onEdit = { viewModel.startEditing(todo) },
                        onDone = { viewModel.isCheckboxChecked(todo) }
                    )
                }
            }
            Log.d("ListTest", "editingTodo: $todos")
        }
    }
}
