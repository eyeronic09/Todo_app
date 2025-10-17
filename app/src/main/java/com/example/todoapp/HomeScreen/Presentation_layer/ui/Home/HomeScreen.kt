package com.example.todoapp.HomeScreen.Presentation_layer.ui.Home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todoapp.HomeScreen.Presentation_layer.ui.component.TodoScreenContent
import org.koin.androidx.compose.koinViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: TodoViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TodoScreenContent(
        state = uiState,
        onEvent = viewModel::onEvent
    )
}
