package com.example.todoapp.todolist.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.todolist.Room.Repository.TodoRepository
import com.example.todoapp.todolist.Room.Todo
import com.example.todoapp.todolist.todoVM


@Composable
fun CardItem(
    viewModel: todoVM,
    todo: Todo,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onDone: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = todo.title,
                textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None

            )

            HorizontalDivider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            SuggestionChip(
                onClick = { null },
                label = { todo.tags?.let { Text(text = it) } },
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text("Shopping") },
                    onClick = {
                        expanded = false
                        todo.tags = "Shopping"
                        viewModel.updateTask(todo.copy(tags = "Shopping"))

                    }
                )
                DropdownMenuItem(
                    text = { Text("Work") },
                    onClick = {
                        expanded = false
                        todo.tags = "Work"
                        viewModel.updateTask(todo.copy(tags = "Work"))
                    }
                )
                DropdownMenuItem(
                    text = { Text("Personal") },
                    onClick = {
                        expanded = false
                        todo.tags = "Personal"
                        viewModel.updateTask(todo.copy(tags = "Personal"))
                    }
                )
            }
            Log.d("Dropdown", "$expanded")
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
                }
                IconButton(onClick = onDone) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Done")
                }
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
                IconButton(onClick = onEdit) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }
            }
        }
    }
}
