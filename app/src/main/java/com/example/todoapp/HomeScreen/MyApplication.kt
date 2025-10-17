package com.example.todoapp.HomeScreen

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.todoapp.HomeScreen.Data_layer.datasource.TodoDataSource
import com.example.todoapp.HomeScreen.Data_layer.local.dao.database.Todo_db
import com.example.todoapp.HomeScreen.Data_layer.local.dao.todoDao
import com.example.todoapp.HomeScreen.Presentation_layer.ui.Home.TodoViewModel
import com.example.todoapp.HomeScreen.Data_layer.local.dao.datasource.RoomTodoDataSource
import com.example.todoapp.HomeScreen.Data_layer.repository.TodoRepositoryImpl
import com.example.todoapp.HomeScreen.Data_layer.repository.TodoRepository
import com.example.todoapp.HomeScreen.Presentation_layer.ui.component.TodoScreenContent
import com.example.todoapp.ui.theme.ToDoAppTheme
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}

val appModule = module {

    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = Todo_db::class.java,
            name = "todo_tb",
        ).build()
    }
    single<todoDao> {
        val database = get<Todo_db>()
        database.todoDao()
    }

    single { Todo_db.getDatabase(get()) }
    single { get<Todo_db>().todoDao() }

    single<TodoDataSource> { RoomTodoDataSource(get()) }
    single<TodoRepository> { TodoRepositoryImpl(get()) }

    viewModelOf(::TodoViewModel)


}