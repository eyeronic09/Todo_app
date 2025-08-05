package com.example.todoapp.todolist

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.todolist.Room.Repository.TodoRepository
import com.example.todoapp.todolist.Room.ToDoDatabase
import com.example.todoapp.todolist.Room.Todo
import com.example.todoapp.todolist.component.CardItem
import com.example.todoapp.todolist.component.TaskInput


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(repository: TodoRepository) {
    val factory = remember { TodoVMFactory(repository) }
    val viewModel: todoVM = viewModel(factory = factory)
    val todos by viewModel.todoList.collectAsState()
    val todoTitle by viewModel.text.collectAsState()
    val editingTodo by viewModel.editingTodo.collectAsState()
    var showDialog by remember { mutableStateOf(false) }



    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(id = R.string.app_name)) })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {
                viewModel.updateTodoText("")
                viewModel.startEditing()
                showDialog = true
            },
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_task)
            )
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Log.d("TodoScreen", "editingTodo: $editingTodo")
            LazyColumn {
                items(
                    items = todos.sortedWith(compareBy({ it.isDone }, { it.tags })),
                    key = { todo -> todo.id }) { todo ->
                    CardItem(
                        viewModel = viewModel,
                        todo = todo,
                        onDelete = { viewModel.deleteTask(todo) },
                        onEdit = {
                            viewModel.startEditing(todo)
                            showDialog = true
                        },
                        onDone = { viewModel.isCheckboxChecked(todo) }
                    )
                    }
                }
            }
            Log.d("ListTest", "ListTodo: $todos")
        }

        if (showDialog) {
            TaskInput(
                onConfirm = {
                viewModel.saveOrUpdateTask()
                showDialog = false
            },
                onDismiss = {
                    viewModel.updateTodoText("")
                    viewModel.startEditing()
                    showDialog = false
                },
                initialText = todoTitle,
                onTextChange = { viewModel.updateTodoText(it) },
                isEditing = editingTodo != null
            )
        }
    }


@Preview
@Composable
fun DefaultPreview(todos: List<Todo> = emptyList()) {
    TodoScreen(
        TodoRepository(
            ToDoDatabase.getDatabase(
                (LocalContext.current)
            ).todoDao()
        )
    )
}