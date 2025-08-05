package com.example.todoapp.todolist.component

import android.util.Log
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.todolist.Room.Todo

@Composable
fun CardItem(todo: Todo, onDelete: () -> Unit, onEdit: () -> Unit, onDone: () -> Unit) {
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
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text("Shopping") },
                    onClick = {
                        expanded = false
                        todo.tags = "Shopping" }
                )
                DropdownMenuItem(
                    text = { Text("Work") },
                    onClick = {
                        expanded = false
                        todo.tags = "Work" }
                )
                DropdownMenuItem(
                    text = { Text("Personal") },
                    onClick = {
                        expanded = false
                        todo.tags = "Personal" }
                )
            }
            Log.d("Dropdown", "$expanded")
            Row(modifier = Modifier.align(Alignment.End)) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
                IconButton(onClick = onDone ) {
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

@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    Column {
        CardItem(
            todo = Todo(
                id = 1,
                title = "Complete the project",
                tags = null,
                isDone = true
            ),
            onDelete = {},
            onEdit = {},
            onDone = {}
        )

        // Preview for a completed task
        CardItem(
            todo = Todo(
                id = 2,
                title = "Buy groceries",
                isDone = false,
                tags = "Shopping"

            ),
            onDelete = {},
            onEdit = {},
            onDone = {}
        )
    }
}