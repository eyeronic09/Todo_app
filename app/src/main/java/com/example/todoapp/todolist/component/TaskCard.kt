package com.example.todoapp.todolist.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.todolist.Room.Todo

@Composable
fun CardItem(todo: Todo, onDelete: () -> Unit, onEdit: () -> Unit ,  onDone: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = todo.title,
                modifier = Modifier.weight(1f),
                textDecoration = if (todo.isDone) TextDecoration.LineThrough else TextDecoration.None
            )
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

@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    Column {
        // Preview for an uncompleted task
        CardItem(
            todo = Todo(
                id = 1,
                title = "Complete the project",
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
                isDone = true
            ),
            onDelete = {},
            onEdit = {},
            onDone = {}
        )
    }
}