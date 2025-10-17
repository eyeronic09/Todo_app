package com.example.todoapp.HomeScreen.Presentation_layer.ui.component

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todoapp.HomeScreen.Presentation_layer.ui.Home.TodoEvent
import com.example.todoapp.HomeScreen.Presentation_layer.ui.Home.TodoState


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoScreenContent(
    state: TodoState,
    onEvent: (TodoEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = state.newTodoTitle,
            onValueChange = { onEvent(TodoEvent.OntitleChange(it)) },
            label = { Text("New Todos") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = { onEvent(TodoEvent.OnAddTodo) },
            enabled = state.newTodoTitle.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Todo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(state.todos) { todo ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = todo.isDone,
                            onCheckedChange = { onEvent(TodoEvent.OnToggleDone(todo)) }
                        )
                        Text(
                            text = todo.title,
                            style = if (todo.isDone){ TextStyle(
                                textDecoration = TextDecoration.LineThrough
                            ) }else {
                                TextStyle.Default
                            },
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { onEvent(TodoEvent.OnDeleteTodo(todo)) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete Todo")
                        }
                    }
                }
            }
        }

    }

}