package com.example.todoapp.todolist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.todolist.Room.Repository.TodoRepository
import com.example.todoapp.todolist.Room.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class todoVM(private val TodoRepository: TodoRepository): ViewModel() {
    private val _todoList = MutableStateFlow<List<Todo>>(emptyList())
    val todoList: StateFlow<List<Todo>> = _todoList.asStateFlow()

    private val _textTitle = MutableStateFlow("")
    val text: StateFlow<String> = _textTitle.asStateFlow()

    private val _editingTodo = MutableStateFlow<Todo?>(null)
    val editingTodo: StateFlow<Todo?> = _editingTodo.asStateFlow()


    fun updateTodoText(text: String) {
        _textTitle.value = text
    }


    fun startEditing(todo: Todo) {
        _editingTodo.value = todo
        _textTitle.value = todo.title
    }

    fun saveOrUpdateTask() {
        val title = _textTitle.value
        if (title.isNotBlank()) {
            val updateTask = _editingTodo.value?.copy(title = title) ?: Todo(title = title)
            updateTask(updateTask)
            _editingTodo.value = null
        }
    }

    private fun updateTask(newTodo: Todo) {
        viewModelScope.launch {
            TodoRepository.update(newTodo)
        }
    }

    fun addTask(todo: Todo) {
        viewModelScope.launch {
            TodoRepository.insert(todo)
        }
    }
    init {
        viewModelScope.launch {
            TodoRepository.getAll().collectLatest {
                _todoList.value = it

            }
        }
    }


}