package com.example.todoapp.todolist.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskInput(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
    initialText: String = "",
    onTextChange: (String) -> Unit,
    isEditing: Boolean = false,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (isEditing) stringResource(id = R.string.edit_task) else stringResource(id = R.string.add_task)) },
        text = {
            TextField(
                value = initialText,
                onValueChange = onTextChange,
                label = { Text(stringResource(id = R.string.task_title)) },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(initialText) }
            ) {
                Text(stringResource(id = android.R.string.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = android.R.string.cancel))
            }
        }

    )
}
@Composable
@Preview(showBackground = true)
fun TaskInputPreview() {
    TaskInput(onConfirm = {}, onDismiss = {}, initialText = "hello world", onTextChange = {})
}