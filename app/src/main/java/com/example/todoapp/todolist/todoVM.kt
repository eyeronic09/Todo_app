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

    private val _selectedTag = MutableStateFlow<String>("All")
    val selectedTag: StateFlow<String?> = _selectedTag.asStateFlow()





    fun updateTodoText(text: String) {
        _textTitle.value = text
    }


    fun startEditing(todo: Todo? = null) {
        _editingTodo.value = todo
        _textTitle.value = todo?.title ?: ""
    }


    fun isCheckboxChecked(todo: Todo){
        viewModelScope.launch {
            val updatedTodo = todo.copy(isDone = !todo.isDone)
            TodoRepository.update(updatedTodo)
        }

    }

    fun saveOrUpdateTask() {
        val title = _textTitle.value
        if (title.isNotBlank()) {
            val currentTag = selectedTag.value ?: ""
            val currentTodo = _editingTodo.value
            if (currentTodo != null) {
                val updatedTodo = currentTodo.copy(
                    title = title,
                    tags = currentTag
                )
                updateTask(updatedTodo)
            } else {
                val newTodo = Todo(
                    title = title,
                    tags = currentTag
                )
                addTask(newTodo)
            }
            
            _editingTodo.value = null
            _textTitle.value = ""
        }
    }

    fun updateTask(newTodo: Todo) {
        viewModelScope.launch {
            TodoRepository.update(newTodo)
        }
    }

    fun addTask(todo: Todo) {
        viewModelScope.launch {
            TodoRepository.insert(todo)
        }
    }
    fun deleteTask(todo: Todo) {
        viewModelScope.launch {
            TodoRepository.delete(todo)
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